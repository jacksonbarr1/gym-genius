package com.example.gymgenius;

import com.example.gymgenius.repository.UserRepository;
import com.example.gymgenius.security.UserDetailsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GymGeniusApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymGeniusApplication.class, args);
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

}
