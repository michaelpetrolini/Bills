package it.mrt.bills.dtos.gas;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class GasOfferDTO {
    private UUID id;
    private String code;
    private String name;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Boolean dualOffer;
    private Boolean hasLimitations;

    private String offerType;
    private String priceType;
    private String customerType;

    private BigDecimal singleRate;
    private BigDecimal gasFixedFee;

    private UUID providerId;

    private List<GasBonusDTO> gasBonuses;
    private List<String> regions;
    private List<String> commonParameters;
}
