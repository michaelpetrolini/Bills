package it.mrt.bills.dtos.energy;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class EnergyOfferDTO {
    private UUID id;
    private String code;
    private String name;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Boolean dualOffer;
    private String offerType;
    private String priceType;
    private String contractType;
    private String customerType;

    private BigDecimal singleRate;
    private BigDecimal f1Rate;
    private BigDecimal f2Rate;
    private BigDecimal f3Rate;
    private BigDecimal f2f3Rate;
    private BigDecimal f1f2Rate;
    private BigDecimal dispatchingRate;
    private BigDecimal energyFixedFee;

    private BigDecimal commercializationCosts;

    private UUID providerId;

    private List<String> commonParameters;
    private List<EnergyBonusDTO> energyBonuses;
}
