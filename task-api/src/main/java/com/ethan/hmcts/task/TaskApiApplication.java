package com.ethan.hmcts.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TaskApiApplication {
	@GetMapping("/test")
	public String test() {
		return "Test to Demo that backend is running";
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskApiApplication.class, args);
	}
}