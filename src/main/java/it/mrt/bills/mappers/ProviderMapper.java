package it.mrt.bills.mappers;

import it.mrt.bills.dtos.ProviderDTO;
import it.mrt.bills.entities.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProviderMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Provider toEntity(ProviderDTO dto);

    ProviderDTO toDto(Provider provider);
}
