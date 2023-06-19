package pl.sda.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Profile("jdbc")
public class AppJdbcConfig {

    @Bean
    public UserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

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
                .requestMatchers(HttpMethod.POST, "/interview/*")
                .hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated();

        return httpSecurity.build();
    }
}
