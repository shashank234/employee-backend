package com.spring.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}
//	@Bean
//    public CommandLineRunner runner(PasswordEncoder passwordEncoder) {
//        return args -> {
//            System.out.println("Encoded password: " + passwordEncoder.encode("Test@123"));
//        };
//    }
}
