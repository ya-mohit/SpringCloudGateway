package com.scgateway.SpringCloudGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	
	//This service runs on 8080 but redirects the calls to 8090 URI post Predicate success
	
	//OR ADD these in application.properties ::
//		server:
//			  port: 8083
//			spring:
//			  cloud:
//			    gateway:
//			      routes:
//			        - id: MySqlSb_Service
//			          uri: http://localhost:8090/
//			          Predicates:
//			            - Path=/MySqlSB/**
//			        - id: MongoSB_Service
//			          uri: http://localhost:8090/
//			          Predicates:
//			            - Path=/MongoSB/**
		
		@Bean
	    public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder){ 
	        return routeLocatorBuilder.routes() 
	                        .route("MySqlSb_Service",r->r.path("/MySqlSB/**") 
	                                .uri("http://localhost:8090/")) 
	                        .route("MongoSB_Service",r->r.path("/MongoSB/**") 
	                                .uri("http://localhost:8090/")).build(); 
	    }
		
}
