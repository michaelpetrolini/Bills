package it.mrt.bills.services;

import it.mrt.bills.configurations.UserPrincipal;
import it.mrt.bills.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).orElse(null);

        if (user == null || username.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}
