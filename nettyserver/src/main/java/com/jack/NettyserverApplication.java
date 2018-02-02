package com.jack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyserverApplication implements CommandLineRunner{
	@Value("${hello}")
	private String world;

	public static void main(String[] args) {
		SpringApplication.run(NettyserverApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("hello is "+world);
	}
}
