package it.mrt.bills.services;

import it.mrt.bills.dtos.UserDTO;
import it.mrt.bills.dtos.filters.UserFilters;
import it.mrt.bills.entities.User;
import it.mrt.bills.mappers.UserMapper;
import it.mrt.bills.repositories.UserRepository;
import it.mrt.bills.repositories.criterias.UserCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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

    public Collection<User> filter(UserFilters filters) {
        Specification<User> specification = UserCriteria.filter(filters);

        return filter(specification);
    }

    public Optional<User> findByEmail(String email) {
        Specification<User> specification = UserCriteria.byEmail(email);

        return findOneOptional(specification);
    }
}
