package com.webrise.assignment.aspect;

import com.webrise.assignment.domain.exseption.EmailAlreadyExistException;
import com.webrise.assignment.domain.exseption.SamePasswordException;
import com.webrise.assignment.domain.exseption.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ErrorResponse handleUserAlreadyExistException (EmailAlreadyExistException exception,
                                                          HttpServletRequest request){
        return errorResponseBuilder(exception, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception,
                                                     HttpServletRequest request){
        return errorResponseBuilder(exception, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SamePasswordException.class)
    public ErrorResponse handleSamePasswordException(SamePasswordException exception,
                                                     HttpServletRequest request){
        return errorResponseBuilder(exception, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleSamePasswordException(Exception exception,
                                                     HttpServletRequest request){
        return errorResponseBuilder(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse errorResponseBuilder(Throwable exception, HttpServletRequest request, HttpStatus status) {
        log.info(getTime() + "\n" + exception.getMessage());
        log.info(request.getMethod() + " " + request.getRequestURI());

        return ErrorResponse.create(exception, status, exception.getMessage());

    }
    private String getTime() {
        return LocalDateTime
                .now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
