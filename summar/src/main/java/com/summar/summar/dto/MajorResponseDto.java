package com.summar.summar.dto;


import com.summar.summar.domain.Major;
import lombok.Getter;
import lombok.Setter;


/**
 * A DTO for the {@link com.summar.summar.domain.Major} entity.
 */

@Getter
@Setter
public class MajorResponseDto {


    private Long majorSeq;

    private String majorName;

    private Long parentsSeq;


    public MajorResponseDto(Major major) {
        this.majorSeq = major.getMajorSeq();
        this.majorName = major.getMajorName();
        this.parentsSeq = major.getParentsSeq();
    }

}
