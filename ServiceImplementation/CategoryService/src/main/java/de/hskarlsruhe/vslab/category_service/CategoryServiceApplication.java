package de.hskarlsruhe.vslab.category_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CategoryServiceApplication {
    public static void main(String... args) {
        System.out.println("Starting spring application from main");
        SpringApplication.run(CategoryServiceApplication.class, args);
    }
}