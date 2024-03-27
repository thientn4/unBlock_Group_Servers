// Maven Springboot initializer:  https://start.spring.io/
// Springboot tutorial: https://www.tutorialspoint.com/spring_boot/index.htm
/*
* MAKE SURE THIS DEPENDENCY IS IN pom.xml TO RUN MICROSERVICE
*
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
*
* */

package com.unBlock_Group_Servers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //------->  marks a class as the starting point for a Spring Boot application
public class UnBlockGroupServersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnBlockGroupServersApplication.class, args);
	}

}
