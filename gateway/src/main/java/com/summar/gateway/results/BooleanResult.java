package com.summar.gateway.results;

import com.summar.gateway.dto.ApiResponseMessage;
import com.summar.gateway.dto.PaginationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
public class BooleanResult {

    public static ResponseEntity<?> build(String resultName, boolean resultStatus) {

        ApiResult apiResult = ApiResult.blank()
                .add(resultName, resultStatus);
            log.info("test");
        return Result.ok(new ApiResponseMessage(apiResult));
    }
}
