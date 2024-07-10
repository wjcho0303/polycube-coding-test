package kr.co.polycube.backendtest.global.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    // NotFound Exception: 404
    NOT_FOUND_EXCEPTION("N001", "요청한 리소스를 찾을 수 없습니다.");

    private final String code;
    private final String message;

}
