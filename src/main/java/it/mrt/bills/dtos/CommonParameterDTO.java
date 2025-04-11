package it.mrt.bills.dtos;

import lombok.Data;

@Data
public class CommonParameterDTO {

    private String name;
    private String category;
    private Double value;
    private Double start;
    private Double end;
}
