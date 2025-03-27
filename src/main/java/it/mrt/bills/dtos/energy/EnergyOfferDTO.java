package it.mrt.bills.dtos.energy;

import lombok.Data;

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
    private String offerType;
    private String priceType;
    private String contractType;
    private String customerType;

    private Double singleRate;
    private Double f1Rate;
    private Double f2Rate;
    private Double f3Rate;
    private Double f2f3Rate;
    private Double f1f2Rate;
    private Double dispatchingRate;
    private Double energyFixedFee;

    private Double commercializationCosts;

    private Double annualDiscount;
    private Double oneTimeDiscount;

    private UUID providerId;

    private List<String> commonParameters;
}
