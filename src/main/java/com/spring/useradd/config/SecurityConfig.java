package com.spring.useradd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.useradd.entity.User;
import com.spring.useradd.repository.userRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private userRepository userRepository;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                        .authorizeHttpRequests(request -> request.requestMatchers("/register").permitAll().anyRequest().authenticated())
                        .formLogin(form -> form.loginPage("/login").permitAll())
                        .build();

    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
            }
            
        });
        return daoAuthenticationProvider;
    }

}
