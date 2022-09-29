package dmaker.goyoun.controller;

import dmaker.goyoun.exception.DMakerErrorResponse;
import dmaker.goyoun.exception.DMakerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by GoYoun 2022/09/23.
 */
@RestController
@Slf4j
public class CMakerController {
  
  @ResponseStatus(value = HttpStatus.CONFLICT)
  @ExceptionHandler(DMakerException.class)
  public DMakerErrorResponse handleException (DMakerException e, HttpServletRequest request) {
    log.error("errorCode : {}, url : {}, message : {}", e.getDMakerErrorCode(), request.getRequestURI(), e.getDetailMessage());
    
    return DMakerErrorResponse.builder()
      .errorCode(e.getDMakerErrorCode())
      .errorMessage(e.getDetailMessage())
      .build();
  }
}
