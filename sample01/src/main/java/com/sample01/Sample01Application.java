package com.sample01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Sample01Application {

	public static void main(String[] args) {
		SpringApplication.run(Sample01Application.class, args);
	}

}
