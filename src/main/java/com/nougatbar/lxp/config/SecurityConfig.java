package com.nougatbar.lxp.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
            // root
            authorize.requestMatchers("/").permitAll();

            // member
            authorize.requestMatchers("/login", "/logout", "/error").permitAll();

            // cart
            authorize.requestMatchers("/cart-ui/**", "/carts/**").permitAll();

            // community
            authorize.requestMatchers("/community-ui/**", "/community/**").permitAll();

            // course
            authorize.requestMatchers("/courses/**").permitAll();

            // enrollment
            authorize.requestMatchers("/enrollment-ui/**", "/enrollments/**").permitAll();

            // order
            authorize.requestMatchers("/order-ui/**", "/orders/**").permitAll();

            // the others
            authorize.anyRequest().authenticated();
        });

        http.formLogin(Customizer.withDefaults());

        http.logout(logout -> {
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/");
        });

        http.sessionManagement(session -> {
            session.maximumSessions(1);
            session.invalidSessionUrl("/");
        });

        return http.build();
    }
}
