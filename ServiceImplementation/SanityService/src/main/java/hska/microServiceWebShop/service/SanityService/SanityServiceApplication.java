package hska.microServiceWebShop.service.SanityService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import hska.microServiceWebShop.Clients.CategoryServiceClient;
import hska.microServiceWebShop.Clients.ProductServiceClient;
import hska.microServiceWebShop.Clients.UserRoleApi;


@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@RibbonClient("sanity-service")
public class SanityServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(SanityServiceApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    CategoryServiceClient categorieServiceClient() {
    	return new CategoryServiceClient();
    }
    
    @Bean
    ProductServiceClient productServiceClient() {
    	return new ProductServiceClient();
    }
    
    @Bean
    UserRoleApi userRoleApi() {
    	return new UserRoleApi();
    }
    
}
