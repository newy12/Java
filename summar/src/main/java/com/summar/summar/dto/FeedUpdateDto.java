package com.summar.summar.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeedUpdateDto {

    @Schema(description = "피드 시퀀스")
    Long feedSeq;

    @Schema(description = "삭제할 이미지 시퀀스")
    List<Long> deleteImageSeqs;

    @Schema(description = "추가할 이미지 파일")
    List<MultipartFile> insertImages = new ArrayList<>();

    @Schema(description = "수정할 내용")
    String contents;

    @Schema(description = "비공개 여부")
    private boolean secretYn;

    @Schema(description = "댓글 허용 여부")
    private boolean commentYn;

    @Schema(description = "임시 저장 여부")
    private boolean tempSaveYn;
}
