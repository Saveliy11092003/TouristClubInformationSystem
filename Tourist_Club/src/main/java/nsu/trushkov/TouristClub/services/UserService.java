package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.Dto.UserDTo;
import nsu.trushkov.TouristClub.entity.User;
import nsu.trushkov.TouristClub.entity.enums.Role;
import nsu.trushkov.TouristClub.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        log.info(user.toString());
        log.info(user.getRole().toString());
        log.info("Saving new user with email {}", email);
        userRepository.save(user);
        return true;
    }


    public List<UserDTo> getUserDTO() {
        List<UserDTo> userDTos = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            userDTos.add(UserDTo.builder().email(user.getEmail()).role(user.getRole().toString())
                    .password(user.getPassword()).build());
        }
        return userDTos;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username " + username);
        User myUser = userRepository.findByEmail(username);
        if (myUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(),
                List.of(myUser.getRole()));

    }

}