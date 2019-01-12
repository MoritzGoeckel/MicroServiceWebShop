package hska.microServiceWebShop.service.SanityService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SanityServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(SanityServiceApplication.class, args);
    }
}
