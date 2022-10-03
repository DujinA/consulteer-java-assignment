package com.consulteer.javaassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavaAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAssignmentApplication.class, args);
    }
}
