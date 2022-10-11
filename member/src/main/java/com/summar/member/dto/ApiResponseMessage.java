package com.summar.member.dto;

import com.summar.member.results.ApiResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseMessage {
    private String status;
    private String message;
    private String errorMessage;
    private String errorCode;
    private ApiResult result;

    public ApiResponseMessage(String status, String message, String errorCode, String errorMessage) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiResponseMessage(ApiResult result) {
        this.status = "SUCCESS";
        this.message = "정상처리";
        this.result = result;
    }

}