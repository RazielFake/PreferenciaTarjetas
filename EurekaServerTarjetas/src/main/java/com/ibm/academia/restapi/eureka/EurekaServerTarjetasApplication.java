package com.ibm.academia.restapi.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerTarjetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerTarjetasApplication.class, args);
	}

}
