package com.bewell.ms.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceUsersApplication {

    public static void main(String[] args) {
        // Retrieve execution profile from environment variable. Otherwise, default profile is used.
        String profile = System.getenv("PROFILE");
        System.setProperty("spring.profiles.active", profile != null ? profile : "default");
        SpringApplication.run(MicroserviceUsersApplication.class, args);
    }

}
