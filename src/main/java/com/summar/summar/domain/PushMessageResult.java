package com.summar.summar.domain;

import com.summar.summar.dto.PushMessageResultDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@Entity
@Slf4j
@NoArgsConstructor
@Table(name = "push_message_result")
public class PushMessageResult extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pushMessageStatusSeq;

    private String multicastId;

    private boolean success;

    private boolean failure;

    private String canonicalIds;


    public PushMessageResult(PushMessageResultDto pushMessageResultDto){
        this.multicastId = pushMessageResultDto.getMulticast_id();
        this.success = pushMessageResultDto.isSuccess();
        this.failure = pushMessageResultDto.isFailure();
        this.canonicalIds = pushMessageResultDto.getCanonical_ids();
    }
}
