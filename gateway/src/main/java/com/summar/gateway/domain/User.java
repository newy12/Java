package com.summar.gateway.domain;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@Getter
@NoArgsConstructor
public class User implements Serializable {

    /**
     * This VO is for security.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;
    private String userId;
    private String userPwd;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userHpNo;
}
