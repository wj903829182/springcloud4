package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistercenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistercenterApplication.class, args);
	}
}
