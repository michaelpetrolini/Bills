package it.mrt.bills.mappers;

import it.mrt.bills.dtos.UserDTO;
import it.mrt.bills.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    User toEntity(UserDTO dto);

    UserDTO toDto(User user);
}
