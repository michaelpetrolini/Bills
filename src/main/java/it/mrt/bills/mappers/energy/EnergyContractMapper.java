package it.mrt.bills.mappers.energy;

import it.mrt.bills.dtos.energy.EnergyContractDTO;
import it.mrt.bills.entities.energy.EnergyContract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EnergyContractMapper {

    @Mappings({
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "energyOffer", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true)
    })
    EnergyContract toEntity(EnergyContractDTO dto);

    @Mappings({
            @Mapping(source = "energyContract.user.id", target = "userId"),
            @Mapping(source = "energyContract.energyOffer.id", target = "offerId")
    })
    EnergyContractDTO toDto(EnergyContract energyContract);
}
