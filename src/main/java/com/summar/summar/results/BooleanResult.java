package com.summar.summar.results;

import com.summar.summar.dto.ApiResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
public class BooleanResult {

    public static ResponseEntity<?> build(String resultName,boolean resultStatus,  String message , List<String> messageResult) {

        ApiResult apiResult = ApiResult.blank()
                .add(resultName, resultStatus)
                        .add(message,messageResult);

            log.info("test");
        return Result.ok(new ApiResponseMessage(apiResult));
    }
}
