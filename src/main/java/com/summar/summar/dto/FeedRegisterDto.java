package com.summar.summar.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;


/**
 * A DTO for the {@link com.summar.summar.domain.Feed} entity.
 */

@Getter
@Setter
@Builder
public class FeedRegisterDto implements Serializable {

    @Schema(description = "유저 시퀀스")
    private Long userSeq;

    @Schema(description = "피드 내용")
    private String contents;

    @Schema(description = "피드 이미지 파일")
    private List<MultipartFile> images;

    @Schema(description = "비공개 여부")
    private boolean secretYn;

    @Schema(description = "댓글 허용 여부")
    private boolean commentYn;

    @Schema(description = "임시 저장 여부")
    private boolean tempSaveYn;

}
