package kr.co.polycube.backendtest.global.exception.model;

import kr.co.polycube.backendtest.global.exception.ErrorCode;

public class InvalidCharacterException extends CustomException {
    public InvalidCharacterException(String message) {
        super(message, ErrorCode.INVALID_CHARACTER_EXCEPTION);
    }
}
