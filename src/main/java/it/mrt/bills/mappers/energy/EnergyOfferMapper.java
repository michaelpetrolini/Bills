package it.mrt.bills.mappers.energy;

import it.mrt.bills.dtos.energy.EnergyOfferDTO;
import it.mrt.bills.entities.energy.EnergyOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnergyOfferMapper {

    @Mapping(target = "provider", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    EnergyOffer toEntity(EnergyOfferDTO dto);

    @Mapping(source = "energyOffer.provider.id", target = "providerId")
    EnergyOfferDTO toDto(EnergyOffer energyOffer);
}
