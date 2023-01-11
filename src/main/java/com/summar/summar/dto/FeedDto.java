package com.summar.summar.dto;


import com.summar.summar.domain.FeedImage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
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

    @Type(type = "yes_no")
    private boolean secretYn;

    @Type(type = "yes_no")
    private boolean commentYn;

    @Type(type = "yes_no")
    private boolean tempSaveYn;

}
