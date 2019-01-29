package de.hskarlsruhe.vslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	/*
	@LoadBalanced // damit mehrere User darauf zugreifen können, LoadBalancer halt
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}

