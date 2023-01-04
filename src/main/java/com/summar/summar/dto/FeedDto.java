package com.summar.summar.dto;


import com.summar.summar.domain.FeedImage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * A DTO for the {@link com.summar.summar.domain.User} entity.
 */

@Getter
@Setter
@Builder
public class FeedDto {


    private Long feedSeq;

    private Long userSeq;

    private String contents;

    private List<FeedImage> feedImages;

}
