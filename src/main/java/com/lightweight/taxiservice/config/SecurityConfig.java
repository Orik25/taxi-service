package com.lightweight.taxiservice.config;

import com.lightweight.taxiservice.constants.RoleData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ADMIN = RoleData.ADMIN.getRoleName();
    private static final String USER = RoleData.USER.getRoleName();
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests((requests) -> requests
//                        "/available-cars"
                        .requestMatchers("/","/registration","/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/cars/**").hasAnyRole(ADMIN,USER)
                        .requestMatchers(HttpMethod.GET,"/drivers/**").hasAnyRole(ADMIN,USER)
                        .requestMatchers(HttpMethod.DELETE,"/cars/**","/drivers/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT,"/cars/**","/drivers/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST,"/cars/**","/drivers/**").hasRole(ADMIN)
                        .requestMatchers("/available-cars").hasRole(ADMIN)
                        .requestMatchers("/roles/**").hasRole(ADMIN)
                        .requestMatchers("/users/**","/user/**").hasRole(ADMIN)

                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
