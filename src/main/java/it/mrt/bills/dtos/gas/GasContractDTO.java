package it.mrt.bills.dtos.gas;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class GasContractDTO {

    private UUID id;
    private LocalDate activationDate;
    private LocalDate expirationDate;
    private String address;
    private String pdrCode;
    private String customerCode;

    private UUID userId;
    private UUID offerId;
}
