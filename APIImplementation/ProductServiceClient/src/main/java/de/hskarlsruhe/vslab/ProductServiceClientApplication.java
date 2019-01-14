package de.hskarlsruhe.vslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceClientApplication {

	/*
	@LoadBalanced // damit mehrere User darauf zugreifen können, LoadBalancer halt
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceClientApplication.class, args);
	}

}

