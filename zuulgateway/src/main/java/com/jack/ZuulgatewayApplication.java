package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZuulgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulgatewayApplication.class, args);
	}
}
