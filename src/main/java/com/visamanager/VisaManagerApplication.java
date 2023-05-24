package com.visamanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class VisaManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(VisaManagerApplication.class, args);
	}
	@Bean
	public PasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	//@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
		PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
		return args -> {
			UserDetails saad = jdbcUserDetailsManager.loadUserByUsername("saad");
			if(saad == null)
				jdbcUserDetailsManager.createUser(User.withUsername("saad").password(passwordEncoder.encode("1234")).roles("USER").build());

			UserDetails yousr = jdbcUserDetailsManager.loadUserByUsername("yousr");
			if(yousr == null)
				jdbcUserDetailsManager.createUser(User.withUsername("yousr").password(passwordEncoder.encode("5678")).roles("USER").build());

			UserDetails admin = jdbcUserDetailsManager.loadUserByUsername("admin");
			if(admin == null)
				jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN").build());
		};
	}

	/*
	@Bean CommandLineRunner commandLineRunner(ClientAccountService accountService){
		return args -> {
			accountService.addNewRole("USER");
			accountService.addNewRole("ADMIN");

			accountService.addNewClient("user1",
					LocalDate.of(2000, 8, 2),
					"user1@gmail.com",
					"1234", "1234");

			accountService.addNewClient("user2",
					LocalDate.of(2010, 5, 4),
					"user2@gmail.com",
					"5678", "5678");

			accountService.addNewClient("admin",
					LocalDate.of(1995, 2, 1),
					"admin@gmail.com",
					"admin", "admin");

			accountService.addRoleToClient("user1@gmail.com", "USER");
			accountService.addRoleToClient("user2@gmail.com", "USER");

			accountService.addRoleToClient("admin@gmail.com", "USER");
			accountService.addRoleToClient("admin@gmail.com", "ADMIN");

			accountService.removeRoleFromClient("user2@gmail.com", "ADMIN");
		};
	}*/
}
