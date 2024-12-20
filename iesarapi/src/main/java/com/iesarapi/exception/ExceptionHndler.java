package com.iesarapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHndler {
    Logger logger= LoggerFactory.getLogger(ExceptionHndler.class);

 @ExceptionHandler(Exception.class)
public ResponseEntity<AppException> handleExceptoin(String msg){
    AppException appException=new AppException();
    appException.setExcepCode("EX33");
    appException.setExcepDec(msg);
    appException.setExcepDate(LocalDateTime.now());
    logger.error(appException.getExcepDec());
    return new ResponseEntity<AppException>(appException, HttpStatus.INTERNAL_SERVER_ERROR);

}

}
