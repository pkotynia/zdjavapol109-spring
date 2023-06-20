package pl.sda.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
@Profile("prod")
// This class combines both jdbc and oauth2 authentication configuration
public class AppOAuth2Config {

    @Bean
    // Implementation of UserDetailsManager interface returning JDBC implementation of this interface
    public UserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    //In case of JDBC UserDetailsManager we need to provide additionally PasswordEncoder
    //NoOp - No Operation Password Encoder is not recommended for any other purposes than testing
    //in our case we are string passwords in DB in plane text
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        //set basic auth for all requests
        httpSecurity
                //we allow httpBasic authentication - JDBC part
                .httpBasic()
                //returns HttpSecurity type and allow us to proceed with configuration
                .and()
                .oauth2Login(Customizer.withDefaults())
                //provides default login page - this is needed to display redirect to github button
                .formLogin(Customizer.withDefaults());

        //needed to be able to do POSTS :)
        httpSecurity
                .csrf()
                .disable();

        //configuration for all requests to be authenticated
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/interview/delete")
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated();

        return httpSecurity.build();
    }

}
