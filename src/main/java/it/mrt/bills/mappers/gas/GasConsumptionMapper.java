package it.mrt.bills.mappers.gas;

import it.mrt.bills.dtos.gas.GasConsumptionDTO;
import it.mrt.bills.entities.gas.GasConsumption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GasConsumptionMapper {

    @Mapping(target = "gasBill", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    GasConsumption toEntity(GasConsumptionDTO dto);

    @Mapping(source = "gasConsumption.gasBill.id", target = "billId")
    GasConsumptionDTO toDto(GasConsumption gasConsumption);
}
