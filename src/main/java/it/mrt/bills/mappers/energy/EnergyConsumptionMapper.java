package it.mrt.bills.mappers.energy;

import it.mrt.bills.dtos.energy.EnergyConsumptionDTO;
import it.mrt.bills.entities.energy.EnergyConsumption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnergyConsumptionMapper {

    @Mapping(target = "energyBill", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    EnergyConsumption toEntity(EnergyConsumptionDTO dto);

    @Mapping(source = "energyConsumption.energyBill.id", target = "billId")
    EnergyConsumptionDTO toDto(EnergyConsumption energyConsumption);
}
