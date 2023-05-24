package com.visamanager.security;

import com.visamanager.security.services.UserDetailsServicePerso;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;
    private UserDetailsServicePerso userDetailsServicePerso;

    /*
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("5678")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN").build()
        );
    }
    */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();

        //httpSecurity.rememberMe();

        httpSecurity.authorizeHttpRequests().requestMatchers("/register").permitAll();
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/register").permitAll();
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/login").permitAll();
        httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();

        httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");

        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();

        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        httpSecurity.csrf().disable();

        httpSecurity.userDetailsService(userDetailsServicePerso);

        return httpSecurity.build();
    }
}
