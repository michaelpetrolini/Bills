package it.mrt.bills.mappers.gas;

import it.mrt.bills.dtos.gas.GasContractDTO;
import it.mrt.bills.entities.gas.GasContract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GasContractMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "gasBills", ignore = true)
    @Mapping(target = "gasOffer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    GasContract toEntity(GasContractDTO dto);

    @Mapping(source = "gasBill.user.id", target = "userId")
    @Mapping(source = "gasBill.gasOffer.id", target = "offerId")
    GasContractDTO toDto(GasContract gasBill);
}
