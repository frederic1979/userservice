package io.getarrays.userservice;

import io.getarrays.userservice.domain.AppUser;
import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUser(null,"John Doe","john33","Klerobs@17",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Jean Buono","jean33","Klerobs@17",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Michel Buono","michel33","Klerobs@17",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Max Buono","max33","Klerobs@17",new ArrayList<>()));

			userService.addRoleToUser("john33","ROLE_USER");
			userService.addRoleToUser("john33","ROLE_MANAGER");
			userService.addRoleToUser("max33","ROLE_ADMIN");
			userService.addRoleToUser("michel33","ROLE_USER");
			userService.addRoleToUser("jean33","ROLE_USER");

		};
	}
}
