package nsu.trushkov.TouristClub.configuration;

import nsu.trushkov.TouristClub.Dto.UserDTo;
import nsu.trushkov.TouristClub.services.UserDetailService;
import nsu.trushkov.TouristClub.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserService userDetailService) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/planned_camping/create", "/admin_panel", "/admin",
                                "/amateur/create", "/athlete/create", "/coach/create",
                                "/competition/create", "/group/create", "/plan/create",
                                "/route/create", "section/create", "section_work_schedule/create",
                                "/stopping_point/create", "/tourist/create").hasRole("ADMIN")
                        .requestMatchers("/registration", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .userDetailsService(userDetailService)
                .httpBasic(httpBasic -> httpBasic.disable())
                .build();
    }

}
