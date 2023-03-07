package com.summar.summar.domain;

import com.summar.summar.dto.ReportRequestDto;
import com.summar.summar.dto.ReportSaveDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Getter
@Slf4j
@NoArgsConstructor
@Table(name = "REPORT")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportSeq;
    @Column(length = 1000)
    private String reportContent;
    private String reportType;
    @ManyToOne
    @JoinColumn(name = "feed_seq")
    private Feed feed;
    @ManyToOne
    @JoinColumn(name = "feed_comment_seq")
    private FeedComment feedComment;
    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User otherUser;

    @ManyToOne
    @JoinColumn(name = "my_seq")
    private User user;

    public Report(ReportSaveDto reportSaveDto){
        this.reportContent = reportSaveDto.getReportContent();
        this.reportType = reportSaveDto.getReportType();
        this.feed = reportSaveDto.getFeedSeq();
        this.feedComment = reportSaveDto.getFeedCommentSeq();
        this.otherUser = reportSaveDto.getUserSeq();
        this.user = reportSaveDto.getMySeq();
    }
}
