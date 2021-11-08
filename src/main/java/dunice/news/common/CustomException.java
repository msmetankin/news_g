package dunice.news.common;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private int errorCode;

    public CustomException(Errors errorMessage){
        super(errorMessage.getMessage());
        this.errorCode = errorMessage.getCode();
    }
}