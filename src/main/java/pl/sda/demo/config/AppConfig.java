package pl.sda.demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {
    // UserDetailsManager -> inMemory
    @Bean
    public UserDetailsManager userDetailsManager() {
        //this will allow to store users information
        return new InMemoryUserDetailsManager();
    }

    // Initialize Users
    @Bean
    //general mechanism to add additional initialization, configuration or validation after Spring Context is ready
    public InitializingBean initializingBean(UserDetailsManager userDetailsManager) {
        return () -> {
            UserDetails user = User
                    .builder()
                    .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .password("haslo")
                    .username("user")
                    .roles("USER")
                    .build();
            //adding user to inMemoryUserDetails
            userDetailsManager.createUser(user);
        };
    }

    // add configuration of HTTP security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        //set basic auth for all requests
        httpSecurity
                .httpBasic();

        //needed to be able to do POSTS :)
        httpSecurity
                .csrf()
                .disable();

        //configuration for all requests to be authenticated
        httpSecurity
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated();

        return httpSecurity.build();
    }

}
