package com.example.insertquerytestinjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id" ,insertable = false, updatable = false, nullable = false)
    //@JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "team_id")
    private Long teamId;

    public void updateinfo(String name) {
        this.name = name;
    }
}


