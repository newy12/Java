package com.summar.summar.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * A DTO for the {@link com.summar.summar.domain.Feed} entity.
 */

@Getter
@Setter
@Builder
public class FeedRegisterDto {

    private Long userSeq;

    private String contents;

    private List<MultipartFile> images;

}
