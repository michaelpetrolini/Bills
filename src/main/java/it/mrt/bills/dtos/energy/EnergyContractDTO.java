package it.mrt.bills.dtos.energy;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class EnergyContractDTO {
    private UUID id;
    private LocalDate activationDate;
    private LocalDate expirationDate;
    private String address;
    private String podCode;
    private String customerCode;
    private Integer voltageSupply;
    private BigDecimal contractuallyCommittedPower;
    private BigDecimal availablePower;
    private BigDecimal maxPower;
    private String customerType;

    private UUID userId;
    private UUID offerId;
}
