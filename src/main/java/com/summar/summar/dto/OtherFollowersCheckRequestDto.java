package com.summar.summar.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OtherFollowersCheckRequestDto {
    //내 userSeq
    @Schema(description = "내 유저시퀀스")
    private Long userSeq;
    //구경할 userSeq
    @Schema(description = "구경할 유저시퀀스")
    private Long otherSeq;
}
