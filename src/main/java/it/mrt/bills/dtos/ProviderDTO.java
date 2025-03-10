package it.mrt.bills.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ProviderDTO {
    private UUID id;
    private String name;
    private String address;
}
