package com.example.mongodbtest.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document("member")
@Builder
public class Member implements Serializable {
    @Id
    private String _id;
    private String name;
    private String age;
    private String address;
}
