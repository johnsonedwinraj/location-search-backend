package com.wisestep.locationsearch.aop;

import com.wisestep.locationsearch.dto.ErrorResponse;
import com.wisestep.locationsearch.exception.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class RestAdvisor {


    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity<ErrorResponse> handle(ServiceException e) {
        log.error("EXCEPTION OCCURRED =======>\n", e);
        return new ResponseEntity<>(new ErrorResponse(e.getErrorMessage(), e.getErrorCode()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        log.error("EXCEPTION OCCURRED =======>\n", e);
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), "UNKNOWN"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
