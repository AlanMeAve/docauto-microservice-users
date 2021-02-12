package com.sysview.docauto.microservice.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DocautoMicroserviceUsersApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DocautoMicroserviceUsersApplication.class, args);
	}

}
