package com.quinbay.reportGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ReportGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportGatewayApplication.class, args);
	}

}
