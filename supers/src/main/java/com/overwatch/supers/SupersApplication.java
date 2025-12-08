package com.overwatch.supers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SupersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupersApplication.class, args);
	}

}
