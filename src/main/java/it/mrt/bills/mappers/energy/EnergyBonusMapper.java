package it.mrt.bills.mappers.energy;

import it.mrt.bills.dtos.energy.EnergyBonusDTO;
import it.mrt.bills.entities.energy.EnergyBonus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnergyBonusMapper {
    @Mapping(target = "energyOffer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    EnergyBonus toEntity(EnergyBonusDTO dto);

    EnergyBonusDTO toDto(EnergyBonus entity);
}
