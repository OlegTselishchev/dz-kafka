package com.example.dz_kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DzKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DzKafkaApplication.class, args);
	}

}
