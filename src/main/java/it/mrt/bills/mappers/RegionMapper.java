package it.mrt.bills.mappers;

import it.mrt.bills.dtos.RegionDTO;
import it.mrt.bills.entities.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "gasOffers", ignore = true)
    Region toEntity(RegionDTO dto);

    RegionDTO toDto(Region entity);
}
