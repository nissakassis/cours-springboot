package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
// icic on indique où scanner les entités à mapper en db
// Lui il cherche toutes les classes qui contiennent  @ENtity du package com.ex.demo.entitiees
@EntityScan("com.example.demo.*")
public class CoursSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursSpringbootApplication.class, args);
	}

}
