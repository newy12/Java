package com.summar.summar.results;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summar.summar.dto.ApiResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ObjectResult {

    /**
     * Object 타입 ApiResult
     * @param resultObjectName 리스트 객체 이름
     * @param resultObject 리스트 객체
     * @return
     */
    public static ResponseEntity<?> build(String resultObjectName, Object resultObject) {
        ApiResult apiResult = ApiResult.blank()
                .add(resultObjectName, resultObject);
        return Result.ok(new ApiResponseMessage(apiResult).getResult());
    }

    /**
     * DTO Object 타입 ApiResult
     * @param resultObject
     * @return
     */
    public static ResponseEntity<?> build(Object resultObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map resultMap = objectMapper.convertValue(resultObject, Map.class);

        ApiResult apiResult = ApiResult.blank();
        apiResult.putAll(resultMap);
        return Result.ok(new ApiResponseMessage(apiResult));
    }

    public static ResponseEntity<?> ok() {
        return Result.ok(new ApiResponseMessage("SUCCESS", "정상처리", "", ""));
    }
}
