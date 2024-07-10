package kr.co.polycube.backendtest.global.exception.model;

import kr.co.polycube.backendtest.global.exception.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message) {
        super(message, ErrorCode.NOT_FOUND_EXCEPTION);
    }
}
