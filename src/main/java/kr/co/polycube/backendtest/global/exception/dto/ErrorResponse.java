package kr.co.polycube.backendtest.global.exception.dto;

import kr.co.polycube.backendtest.global.exception.model.CustomException;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String errorCode;
    private final String message;
    private final String detailMessage;

    public ErrorResponse(CustomException ex) {
        this.errorCode = ex.getErrorCode().getCode();
        this.message = ex.getErrorCode().getMessage();
        this.detailMessage = ex.getMessage();
    }
}
