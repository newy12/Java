package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportRequestDto {
    private Long mySeq;
    private Long userSeq;
    private Long feedSeq;
    private Long feedCommentSeq;
    private String reportType;
    private String reportContent;
}
