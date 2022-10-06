package com.summar.gateway.common;

import lombok.Getter;

@Getter
public class SummarCommonException extends RuntimeException {

    private String code;

    public SummarCommonException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SummarCommonException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}

enum SummarCommonCode {

}
