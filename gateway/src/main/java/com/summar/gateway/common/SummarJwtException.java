package com.summar.gateway.common;

import lombok.Getter;

@Getter
public class SummarJwtException extends RuntimeException{

    private String code;

    public SummarJwtException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SummarJwtException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
