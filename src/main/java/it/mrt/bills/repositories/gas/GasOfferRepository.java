package it.mrt.bills.repositories.gas;

import it.mrt.bills.dtos.gas.GasComparisonRequestDTO;
import it.mrt.bills.dtos.gas.GasComparisonResultDTO;
import it.mrt.bills.entities.gas.GasOffer;
import it.mrt.bills.repositories.DbEntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GasOfferRepository extends DbEntityRepository<GasOffer> {

    @Query(nativeQuery = true, value = """
            -- Common charges from the general parameter table
            WITH CommonParameters AS (
                SELECT
                    -- Dispatching
                    SUM(CASE WHEN category = 'GAS_DISPATCHING_FIXED_QUOTA' THEN value ELSE 0 END) AS dispatching_fixed_quota,
                    SUM(CASE WHEN category = 'GAS_DISPATCHING_ENERGY_QUOTA' AND start IS NULL THEN value ELSE 0 END) AS dispatching_energy_quota_not_ranged,
                    SUM(CASE WHEN category = 'GAS_DISPATCHING_ENERGY_QUOTA' AND start IS NOT NULL
                        THEN GREATEST(value * LEAST(COALESCE(end, :#{#request.consumption}) - start, :#{#request.consumption} - start), 0)
                        ELSE 0 END) AS dispatching_energy_quota_ranged,
            
                    -- System Charges
                    SUM(CASE WHEN category = 'GAS_SYSTEM_CHARGES_FIXED_QUOTA' THEN value ELSE 0 END) AS system_charges_fixed_quota,
                    SUM(CASE WHEN category = 'GAS_SYSTEM_CHARGES_ENERGY_QUOTA' AND start IS NULL THEN value ELSE 0 END) AS system_charges_energy_quota_not_ranged,
                    SUM(CASE WHEN category = 'GAS_SYSTEM_CHARGES_ENERGY_QUOTA' AND start IS NOT NULL
                        THEN GREATEST(value * LEAST(COALESCE(end, :#{#request.consumption}) - start, :#{#request.consumption} - start), 0)
                        ELSE 0 END) AS system_charges_energy_quota_ranged,
            
                    -- Taxes
                    SUM(CASE WHEN category = 'GAS_TAXES_VARIABLE' AND start IS NULL THEN value ELSE 0 END) AS taxes_not_ranged,
                    SUM(CASE WHEN category = 'GAS_TAXES_VARIABLE' AND start IS NOT NULL
                        THEN GREATEST(value * LEAST(COALESCE(end, :#{#request.consumption}) - start, :#{#request.consumption} - start), 0)
                        ELSE 0 END) AS taxes_ranged,
            
                    -- VAT
                    SUM(CASE
                        WHEN category IN ('GAS_DISPATCHING_ENERGY_QUOTA', 'GAS_SYSTEM_CHARGES_ENERGY_QUOTA', 'GAS_TAXES_VARIABLE')\s
                            AND end <= 480
                        THEN GREATEST(value * LEAST(COALESCE(end, :#{#request.consumption}) - start, :#{#request.consumption} - start), 0)\s
                        ELSE 0
                    END) AS vat_10_quota,
            
                    SUM(CASE
                        WHEN category IN ('GAS_DISPATCHING_ENERGY_QUOTA', 'GAS_SYSTEM_CHARGES_ENERGY_QUOTA', 'GAS_TAXES_VARIABLE')
                            AND start >= 480
                        THEN GREATEST(value * LEAST(COALESCE(end, :#{#request.consumption}) - start, :#{#request.consumption} - start), 0)
                        ELSE 0
                    END) AS vat_22_quota
            
                FROM bills.common_parameter
            ),
            
            -- Optional extra fees linked to specific gas offers
            OptionalParameters AS (
                SELECT
                    o.id AS offer_id,
                    SUM(CASE WHEN category = 'GAS_FIXED' THEN p.value ELSE 0 END) AS gas_fixed,
                    SUM(CASE WHEN category = 'GAS_VARIABLE' THEN p.value ELSE 0 END) AS gas_variable
                FROM bills.gas_offer o
                LEFT JOIN bills.gas_offer_common_parameter gp ON o.id = gp.gas_offer_id
                LEFT JOIN bills.common_parameter p ON p.id = gp.common_parameter_id
                GROUP BY o.id
            ),
            
            -- Bonuses applied to offers
            Bonuses AS (
                SELECT
                    offer_id,
                    SUM(CASE WHEN subject_to_taxation THEN value ELSE 0 END) AS taxable_bonus,
                    SUM(CASE WHEN NOT subject_to_taxation THEN value ELSE 0 END) AS non_taxable_bonus
                FROM bills.gas_bonus
                WHERE applicability_condition = 'ALWAYS_APPLICABLE' AND validity != 'OVER_12_MONTHS'
                GROUP BY offer_id
            ),
            
            -- Final cost calculation per offer
            Costs AS (
                SELECT
                    go.code,
                    go.name,
                    -- Energy cost
                    gas_fixed_fee + COALESCE(op.gas_fixed, 0) +
                        (single_rate + COALESCE(op.gas_variable, 0)) * :#{#request.consumption} AS gas_cost,
            
                    -- Dispatching
                    cp.dispatching_fixed_quota +
                    cp.dispatching_energy_quota_ranged +
                    cp.dispatching_energy_quota_not_ranged * :#{#request.consumption} AS dispatching_cost,
            
                    -- System Charges
                    cp.system_charges_fixed_quota +
                    cp.system_charges_energy_quota_ranged +
                    cp.system_charges_energy_quota_not_ranged * :#{#request.consumption} AS system_charges,
            
                    -- Taxes
                    cp.taxes_ranged +
                    cp.taxes_not_ranged * :#{#request.consumption} AS taxes,
            
                    -- VAT 10%
                    ((single_rate + COALESCE(op.gas_variable, 0) +
                      cp.dispatching_energy_quota_not_ranged +
                      cp.system_charges_energy_quota_not_ranged +
                      cp.taxes_not_ranged) * 480 + cp.vat_10_quota) * 0.10 AS vat_10,
            
                    -- VAT 22%
                    ((single_rate + COALESCE(op.gas_variable, 0) +
                      cp.dispatching_energy_quota_not_ranged +
                      cp.system_charges_energy_quota_not_ranged +
                      cp.taxes_not_ranged) * GREATEST(:#{#request.consumption} - 480, 0) +
                      gas_fixed_fee + COALESCE(op.gas_fixed, 0) +
                      cp.dispatching_fixed_quota + cp.system_charges_fixed_quota +
                      cp.vat_22_quota - COALESCE(b.taxable_bonus, 0)) * 0.22 AS vat_22,
            
                    -- Bonuses
                    COALESCE(b.taxable_bonus, 0) + COALESCE(b.non_taxable_bonus, 0) AS bonus,
                    COALESCE(b.taxable_bonus, 0) AS taxable_bonus,
                    COALESCE(b.non_taxable_bonus, 0) AS non_taxable_bonus
            
                FROM bills.gas_offer go
                JOIN CommonParameters cp ON 1=1
                LEFT JOIN Bonuses b ON go.id = b.offer_id
                LEFT JOIN OptionalParameters op ON go.id = op.offer_id
                JOIN bills.gas_offer_region gor ON gor.gas_offer_id = go.id
                JOIN bills.region r ON gor.region_id = r.id
                WHERE
                    go.price_type = :#{#request.priceType}
                    AND NOT go.dual_offer
                    AND NOT go.has_limitations
                    AND CURRENT_DATE() BETWEEN go.valid_from AND go.valid_to
                    AND r.name = :#{#request.region}
            )
            
            -- Final output
            SELECT
                code,
                name,
                gas_cost,
                dispatching_cost,
                system_charges,
                taxes,
                vat_10 + vat_22 AS vat,
                bonus,
                gas_cost + dispatching_cost + system_charges + taxes + vat_10 + vat_22 - bonus AS total_amount
            FROM Costs
            ORDER BY total_amount;
            """)
    List<GasComparisonResultDTO> calculateComparison(@Param("request") GasComparisonRequestDTO request);
}
