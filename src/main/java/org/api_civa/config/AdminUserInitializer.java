package org.api_civa.config;

import org.api_civa.dto.UserDTO;
import org.api_civa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final UserService userService;

    public AdminUserInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        if (userService.findByUsername("admin").isEmpty()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername("admin");
            userDTO.setPassword("admin");
            userDTO.setRole("ADMIN");

            userService.save(userDTO, null);
            System.out.println("Usuario administrador creado");
        }
    }

}
