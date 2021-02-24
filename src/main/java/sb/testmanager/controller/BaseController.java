package sb.testmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import sb.testmanager.controller.exceptions.AlreadyExistException;
import sb.testmanager.controller.exceptions.ExceptionDto;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
public class BaseController {

    @ResponseBody
    @ResponseStatus(CONFLICT)
    @ExceptionHandler(AlreadyExistException.class)
    public ExceptionDto handleAlreadyExistException(Exception e) {
        log.warn("AlreadyExistException: ", e);
        return new ExceptionDto(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("MethodArgumentNotValidException: ", e);
        return e.getBindingResult().getAllErrors().stream()
                .map(error -> new ExceptionDto(mapObjectError(error)))
                .collect(Collectors.toList());
    }

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionDto handleException(Exception ex) {
        log.error("Internal Server Error: ", ex);
        return new ExceptionDto("Internal server error. Please contact support");
    }

    private String mapObjectError(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            FieldError fieldError = (FieldError) objectError;
            String fieldName = fieldError.getField();
            return "Invalid object: " + objectError.getObjectName() + "." + fieldName + " - " + objectError.getDefaultMessage();
        }
        return "Invalid object: " + objectError.getObjectName() + " - " + objectError.getDefaultMessage();
    }
}
