package com.summar.member.results;

import com.summar.member.dto.ApiResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class BooleanResult {

    public static ResponseEntity<?> build(String resultName, boolean resultStatus) {

        ApiResult apiResult = ApiResult.blank()
                .add(resultName, resultStatus);
        log.info("test");
        return Result.ok(String.valueOf(new ApiResponseMessage(apiResult)));
    }
}
