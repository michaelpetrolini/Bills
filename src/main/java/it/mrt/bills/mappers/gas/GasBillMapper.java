package it.mrt.bills.mappers.gas;


import it.mrt.bills.dtos.gas.GasBillDTO;
import it.mrt.bills.entities.gas.GasBill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GasBillMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "gasOffer", ignore = true)
    @Mapping(target = "gasContract", ignore = true)
    @Mapping(target = "gasConsumptions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    GasBill toEntity(GasBillDTO dto);

    @Mapping(source = "gasBill.user.id", target = "userId")
    @Mapping(source = "gasBill.gasOffer.id", target = "offerId")
    @Mapping(source = "gasBill.gasContract.id", target = "contractId")
    GasBillDTO toDto(GasBill gasBill);
}
