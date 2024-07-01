package com.quinbay.ordersGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrdersGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersGatewayApplication.class, args);
	}

}
