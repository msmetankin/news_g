package dunice.news.common;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseErrorDTO> customExceptionHandler(CustomException ex){
        List<Integer> errorCodes = new ArrayList<>();
        errorCodes.add(ex.getErrorCode());

        ResponseErrorDTO responseError = ResponseErrorDTO.builder()
                .codes(errorCodes)
                .statusCode(ex.getErrorCode())
                .timeStamp(LocalDateTime.now().toString())
                .build();

        return ResponseEntity
                .badRequest()
                .body(responseError);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDTO> bodyValidationExceptionHandler(MethodArgumentNotValidException ex){
        String message = ex.getFieldErrors().get(0).getDefaultMessage();
        List<Integer> errorCodes = Collections.singletonList(Errors.errorCodes.get(message));

        ResponseErrorDTO responseError = ResponseErrorDTO.builder()
                .codes(errorCodes)
                .statusCode(errorCodes.stream().findFirst().get())
                .timeStamp(LocalDateTime.now().toString())
                .build();

        return ResponseEntity
                .badRequest()
                .body(responseError);
    }
}
