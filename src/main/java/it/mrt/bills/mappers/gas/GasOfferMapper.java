package it.mrt.bills.mappers.gas;

import it.mrt.bills.dtos.gas.GasOfferDTO;
import it.mrt.bills.entities.gas.GasOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GasOfferMapper {
    @Mapping(target = "provider", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "commonParameters", ignore = true)
    @Mapping(target = "gasBonuses", ignore = true)
    @Mapping(target = "regions", ignore = true)
    GasOffer toEntity(GasOfferDTO dto);

    @Mapping(target = "providerId", ignore = true)
    @Mapping(target = "regions", ignore = true)
    @Mapping(target = "commonParameters", ignore = true)
    GasOfferDTO toDto(GasOffer entity);
}
