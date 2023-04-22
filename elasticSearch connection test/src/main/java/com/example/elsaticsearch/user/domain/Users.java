package com.example.elsaticsearch.user.domain;

import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.persistence.*;

@Document(indexName = "users")
@Entity
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private BasicProfile basicProfile;

    protected Users() {
    }

    public Users(String name, String description) {
        this(null, new BasicProfile(name, description));
    }

    @PersistenceConstructor
    public Users(Long id, BasicProfile basicProfile) {
        this.id = id;
        this.basicProfile = basicProfile;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return basicProfile.getName();
    }

    public String getDescription() {
        return basicProfile.getDescription();
    }
}
