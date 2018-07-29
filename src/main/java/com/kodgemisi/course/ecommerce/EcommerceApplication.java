package com.kodgemisi.course.ecommerce;

import com.kodgemisi.course.ecommerce.user.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.imageio.spi.RegisterableService;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService userService, RegistrationService registrationService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (roleRepository.findAll().isEmpty()) {
                roleRepository.save(Role.ADMIN);
                roleRepository.save(Role.USER);
            }

            if (!userService.existsByUsername("admin")) {
                // Create new user
                User user = new User();
                user.setUsername("user");
                user.setEmail("user-lyk@mailinator.com");
                user.setPassword(passwordEncoder.encode("password"));
                user.setFirstName("User");
                user.setLastName("User");
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByRoleName(Role.RoleName.USER));
                user.setRoles(roles);

                // Create new admin
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin-lyk@mailinator.com");
                admin.setPassword(passwordEncoder.encode("password"));
                admin.setFirstName("Admin");
                admin.setLastName("Admin");
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(roleRepository.findByRoleName(Role.RoleName.ADMIN));
                admin.setRoles(adminRoles);

                // Create user has role both admin and user
                User adminUser = new User();
                adminUser.setUsername("adminuser");
                adminUser.setEmail("adminuser-lyk@mailinator.com");
                adminUser.setPassword(passwordEncoder.encode("password"));
                adminUser.setFirstName("Adminuser");
                adminUser.setLastName("Admin");
                Set<Role> adminUserRoles = new HashSet<>();
                adminUserRoles.add(roleRepository.findByRoleName(Role.RoleName.ADMIN));
                adminUserRoles.add(roleRepository.findByRoleName(Role.RoleName.USER));
                adminUser.setRoles(adminUserRoles);

                registrationService.createAdmin(user);
                registrationService.createAdmin(admin);
                registrationService.createAdmin(adminUser);
            }
        };
    }
}
