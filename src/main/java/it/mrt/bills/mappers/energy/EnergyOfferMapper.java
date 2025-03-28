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
    @Mapping(target = "commonParameters", ignore = true)
    @Mapping(target = "energyBonuses", ignore = true)
    EnergyOffer toEntity(EnergyOfferDTO dto);

    @Mapping(source = "energyOffer.provider.id", target = "providerId")
    @Mapping(target = "commonParameters", ignore = true)
    EnergyOfferDTO toDto(EnergyOffer energyOffer);
}
