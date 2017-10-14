package com.tomkasp.springhazelcst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringHazelcstApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHazelcstApplication.class, args);
    }
}
