package it.mrt.bills.mappers;

import it.mrt.bills.dtos.CommonParameterDTO;
import it.mrt.bills.entities.CommonParameter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommonParameterMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "energyOffers", ignore = true)
    CommonParameter toEntity(CommonParameterDTO dto);

    CommonParameterDTO toDto(CommonParameter commonParameter);
}
