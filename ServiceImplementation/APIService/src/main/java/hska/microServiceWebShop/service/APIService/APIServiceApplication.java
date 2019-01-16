package hska.microServiceWebShop.service.APIService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import hska.microServiceWebShop.ApiClient;



@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@RibbonClient("api-service")
public class APIServiceApplication {
	
	private static final String SERVICEID = "sanity-service";
	
    public static void main(String[] args){
        SpringApplication.run(APIServiceApplication.class, args);
    }
    
    @Autowired
    private LoadBalancerClient loadBalancer;
    
    @Autowired
	private DiscoveryClient discoveryClient;

	@Bean
	public String getServiceUrl() {
	    List<ServiceInstance> instances = discoveryClient.getInstances(SERVICEID);
	    if(instances.size() > 0) {
	    	ServiceInstance service = loadBalancer.choose(SERVICEID);
	    	return service.getUri().toString();
	    }
	    return "http://localhost:8081";
	}
	
	@Autowired
	private String serviceUrl;
	
	@Bean
	public hska.microServiceWebShop.api.UserRoleApi userRolesApi() {
		return new hska.microServiceWebShop.api.UserRoleApi(new ApiClient().setBasePath(serviceUrl));
	}
	
	@Bean
	public hska.microServiceWebShop.api.CategoriesApi categoryApi() {
		return new hska.microServiceWebShop.api.CategoriesApi(new ApiClient().setBasePath(serviceUrl));
	}
	
	@Bean
	public hska.microServiceWebShop.api.ProductsApi productApi() {
		return new hska.microServiceWebShop.api.ProductsApi(new ApiClient().setBasePath(serviceUrl));
	}
	
}
