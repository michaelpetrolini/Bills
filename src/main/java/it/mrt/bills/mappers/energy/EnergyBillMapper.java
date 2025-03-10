package it.mrt.bills.mappers.energy;

import it.mrt.bills.dtos.energy.EnergyBillDTO;
import it.mrt.bills.entities.energy.EnergyBill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnergyBillMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "energyOffer", ignore = true)
    @Mapping(target = "energyContract", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    EnergyBill toEntity(EnergyBillDTO dto);

    @Mapping(source = "energyBill.user.id", target = "userId")
    @Mapping(source = "energyBill.energyOffer.id", target = "offerId")
    @Mapping(source = "energyBill.energyContract.id", target = "contractId")
    EnergyBillDTO toDto(EnergyBill energyBill);
}
