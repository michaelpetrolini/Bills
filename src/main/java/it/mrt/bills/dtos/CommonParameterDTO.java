package it.mrt.bills.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommonParameterDTO {

    private String name;
    private String category;
    private BigDecimal value;
    private Integer start;
    private Integer end;
}
