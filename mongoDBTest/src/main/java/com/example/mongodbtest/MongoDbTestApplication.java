package com.example.mongodbtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.mongodbtest")
public class MongoDbTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbTestApplication.class, args);
    }
    //mongodb crud
}
