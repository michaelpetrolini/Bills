package it.mrt.bills.services;

import it.mrt.bills.dtos.UserDTO;
import it.mrt.bills.entities.User;
import it.mrt.bills.mappers.UserMapper;
import it.mrt.bills.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DbEntityService<User> {

    @Autowired
    private UserMapper mapper;

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public User save(UserDTO dto) {
        User user = mapper.toEntity(dto);
        return save(user);
    }
}
