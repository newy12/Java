package com.summar.summar.dto;

import com.summar.summar.domain.Feed;
import com.summar.summar.domain.FeedComment;
import com.summar.summar.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportSaveDto {
    private User mySeq;
    private User userSeq;
    private Feed feedSeq;
    private FeedComment feedCommentSeq;
    private String reportType;
    private String reportContent;
}
