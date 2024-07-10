package kr.co.polycube.backendtest.global.exception;

import kr.co.polycube.backendtest.global.exception.dto.ErrorResponse;
import kr.co.polycube.backendtest.global.exception.model.CustomException;
import kr.co.polycube.backendtest.global.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Not Found Exception
     *
     * API URL 을 찾을수 없는 경우는 NoHandlerFoundException 을 통해 핸들링
     * */
    @ExceptionHandler({NotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(Exception ex) {

        CustomException customException;
        if (ex instanceof NoHandlerFoundException) {
            customException = new NotFoundException(ex.getMessage(), ErrorCode.NOT_FOUND_EXCEPTION);
        } else {
            customException = (NotFoundException) ex;
        }

        ErrorResponse response = new ErrorResponse(customException);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
