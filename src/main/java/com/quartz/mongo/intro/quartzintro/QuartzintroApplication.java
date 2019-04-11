package com.quartz.mongo.intro.quartzintro;

import com.quartz.mongo.intro.quartzintro.constants.Node;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzintroApplication {

	public static void main(String[] args) {
		Node.createNode(args[0]);
		SpringApplication.run(QuartzintroApplication.class, args);
	}
}
