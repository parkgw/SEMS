package sems.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.google.gson.Gson;

import sems.config.exception.SemsException;

@ControllerAdvice
public class WebWideExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(WebWideExceptionHandler.class);

  @ExceptionHandler(SemsException.class)
  @ResponseStatus(value=HttpStatus.BAD_REQUEST)
  public @ResponseBody ResponseEntity<?> handleError(HttpServletRequest request, HttpServletResponse response, SemsException e) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", "application/json;charset=UTF-8");

    return new ResponseEntity<String>(new Gson().toJson(e.getErrorObject()), httpHeaders, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(NoHandlerFoundException.class)
  public String handle(Exception e) {
    return "404";
  }
}
