package com.dev.backend;

import com.dev.backend.enums.PermissionName;
import com.dev.backend.models.Permission;
import com.dev.backend.repositories.PermissionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@SpringBootApplication
public class BackendApplication {


    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PermissionRepository permissionRepository) {
        return args -> {
//            for (PermissionName permissionName : PermissionName.values()) { // Save All rules in db
//
//                try {
//                    permissionRepository.save(new Permission(permissionName));
//                } catch (Exception ex) {
//                }
//            }
        };
    }
}
