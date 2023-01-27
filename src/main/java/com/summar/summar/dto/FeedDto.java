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

    private SimpleUserVO user;

    private String contents;

    private List<FeedImage> feedImages;

    private boolean secretYn;

    private boolean commentYn;

    private boolean tempSaveYn;

    private boolean activated;

}
