package nsu.trushkov.TouristClub.services;

import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.User;
import nsu.trushkov.TouristClub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {
    @Autowired
    // CRUD-репозиторий для доступа к пользовательским данным
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if( user == null )
            throw new UsernameNotFoundException(username);

        log.info("user " + username);
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}

