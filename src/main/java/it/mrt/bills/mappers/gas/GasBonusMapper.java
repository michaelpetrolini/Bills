package it.mrt.bills.mappers.gas;

import it.mrt.bills.dtos.gas.GasBonusDTO;
import it.mrt.bills.entities.gas.GasBonus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GasBonusMapper {
    @Mapping(target = "gasOffer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    GasBonus toEntity(GasBonusDTO dto);

    GasBonusDTO toDto(GasBonus entity);
}
