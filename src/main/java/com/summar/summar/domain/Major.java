package com.summar.summar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.summar.summar.dto.LoginRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MAJOR")
public class Major {
//커밑체스트
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="major_seq")
    private Long majorSeq;
    private String majorName;
    private Long parentsSeq;
}
