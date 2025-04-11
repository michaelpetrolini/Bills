package it.mrt.bills.repositories.energy;

import it.mrt.bills.dtos.energy.EnergyComparisonRequestDTO;
import it.mrt.bills.dtos.energy.EnergyComparisonResultDTO;
import it.mrt.bills.entities.energy.EnergyOffer;
import it.mrt.bills.repositories.DbEntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnergyOfferRepository extends DbEntityRepository<EnergyOffer> {
    @Query(nativeQuery = true, value = """
            -- Define constant values
            WITH Parameters AS (
                SELECT
                    :#{#request.f1Consumption} AS f1_consumption,
                    :#{#request.f2Consumption} AS f2_consumption,
                    :#{#request.f3Consumption} AS f3_consumption,
                    :#{#request.powerSupply} AS power_kw,

                    :#{#request.f1Consumption} + :#{#request.f2Consumption} AS f12_consumption,
                    :#{#request.f2Consumption} + :#{#request.f3Consumption} AS f23_consumption,
                    :#{#request.f1Consumption} + :#{#request.f2Consumption} + :#{#request.f3Consumption} AS total_consumption
            ),
            -- Dispatching Quotas
            DispatchingQuotas AS (
                SELECT
                    SUM(CASE WHEN category = 'ENERGY_DISPATCHING_FIXED_QUOTA' THEN value ELSE 0 END) AS dispatching_fixed,
                    SUM(CASE WHEN category = 'ENERGY_DISPATCHING_POWER_QUOTA' THEN value ELSE 0 END) AS dispatching_power,
                    SUM(CASE WHEN category = 'ENERGY_DISPATCHING_ENERGY_QUOTA' THEN value ELSE 0 END) AS dispatching_energy,
                    SUM(CASE WHEN category = 'ENERGY_SYSTEM_CHARGES_ENERGY_QUOTA' THEN value ELSE 0 END) AS system_charges_power_quota,
                    SUM(CASE WHEN category = 'ENERGY_TAXES_VARIABLE' THEN value ELSE 0 END) AS taxes_variable
                FROM bills.common_parameter
            ),
            -- Bonuses
            Bonuses AS (
                SELECT
                    offer_id,
                    SUM(CASE WHEN subject_to_taxation THEN value ELSE 0 END) AS taxable_bonus,
                    SUM(CASE WHEN NOT subject_to_taxation THEN value ELSE 0 END) AS non_taxable_bonus
                FROM bills.energy_bonus
                WHERE applicability_condition = 'ALWAYS_APPLICABLE' AND validity != 'OVER_12_MONTHS'
                GROUP BY offer_id
            ),
            -- Base Calculations
            BaseCalculations AS (
                SELECT
                    o.code,
                    o.name,
                    -- Base price calculation
                    (COALESCE(o.single_rate, 0) + COALESCE(o.f1rate, 0)) * f1_consumption +
                    COALESCE(o.f2rate, 0) * f2_consumption +
                    COALESCE(o.f3rate, 0) * f3_consumption +
                    COALESCE(o.f1f2rate, 0) * f12_consumption +
                    COALESCE(o.f2f3rate, 0) * f23_consumption AS base_price,
            
                    -- Fixed energy cost
                    o.energy_fixed_fee +
                    COALESCE((
                        SELECT SUM(p.value)
                        FROM bills.energy_offer_common_parameter op
                        JOIN bills.common_parameter p ON p.id = op.common_parameter_id
                        WHERE op.energy_offer_id = o.id AND p.category = 'ENERGY_FIXED'
                    ), 0) + o.commercialization_costs AS energy_fixed,
            
                    -- Variable energy cost
                    (COALESCE(o.dispatching_rate, 0) +
                    COALESCE((
                        SELECT SUM(p.value)
                        FROM bills.energy_offer_common_parameter op
                        JOIN bills.common_parameter p ON p.id = op.common_parameter_id
                        WHERE op.energy_offer_id = o.id AND p.category = 'ENERGY_VARIABLE'
                    ), 0)) * total_consumption AS energy_variable,
            
                    -- Bonuses
                    COALESCE(o.one_time_discount, 0) + COALESCE(o.annual_discount, 0) AS bonus,
            
                    -- Taxable consumption calculation
                    CASE
                        WHEN total_consumption <= 1800 THEN 0
                        WHEN total_consumption <= 2640 THEN total_consumption - 1800
                        ELSE total_consumption - (1800 - total_consumption + 2640)
                    END AS taxable_consumption,
            
                    COALESCE(b.taxable_bonus, 0) AS taxable_bonus,
                    COALESCE(b.non_taxable_bonus, 0) AS non_taxable_bonus
                FROM bills.energy_offer o
                JOIN Parameters ON 1=1
                LEFT JOIN Bonuses b ON o.id = b.offer_id
                WHERE o.price_type = :#{#request.priceType}
                  AND o.customer_type = :#{#request.offerType}
                  AND o.contract_type != :#{#request.contractType}
                  AND NOT o.dual_offer
                  AND CURRENT_DATE() BETWEEN o.valid_from AND o.valid_to
            ),
            -- Compute Energy Costs
            EnergyCosts AS (
                SELECT
                    bc.*,
                    ROUND(bc.base_price + bc.energy_fixed + bc.energy_variable, 2) AS energy_cost
                FROM BaseCalculations bc
            ),
            -- Compute Final Costs with Taxes
            EnergyCostsWithTaxes AS (
                SELECT
                    ec.code,
                    ec.name,
                    ec.energy_cost,
                    ROUND(dq.dispatching_fixed + dq.dispatching_power * power_kw + dq.dispatching_energy * total_consumption, 2) AS dispatching_cost,
                    ROUND(dq.system_charges_power_quota * total_consumption, 2) AS system_charges,
                    ROUND(ec.taxable_bonus + ec.non_taxable_bonus, 2) AS bonus,
                    ROUND(ec.taxable_consumption * dq.taxes_variable, 2) AS excise_tax,
                    ROUND((ec.energy_cost +
                    (dq.dispatching_fixed + dq.dispatching_power * power_kw + dq.dispatching_energy * total_consumption) +
            		(dq.system_charges_power_quota * total_consumption) +
            		(ec.taxable_consumption * dq.taxes_variable) - ec.taxable_bonus) * 0.1, 2) AS taxes
                FROM EnergyCosts ec, DispatchingQuotas dq, Parameters
            )
            -- Final Output
            SELECT
                *,
                ROUND(energy_cost + dispatching_cost + system_charges + excise_tax + taxes - bonus, 2) AS total_amount
            FROM EnergyCostsWithTaxes
            ORDER BY total_amount;
            """)
    List<EnergyComparisonResultDTO> calculateComparison(@Param("request") EnergyComparisonRequestDTO request);
}
