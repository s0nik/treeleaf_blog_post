package com.blog.post.config.response;

import com.blog.post.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
@Controller
public class MyExceptionHandler {

  ServiceResponse serviceResponse;

  private final static Logger LOGGER = Logger.getLogger(MyExceptionHandler.class.getName());


  @ResponseBody
  @ExceptionHandler
  public ResponseEntity<?> handleMyException(Exception exception) {
    HttpStatus status = HttpStatus.OK;
    serviceResponse = new ServiceResponse();

    if (exception instanceof MyException) {
      MyException ex = (MyException) exception;
      serviceResponse.setData(ex.getData());
      serviceResponse.setMessage(ex.getMessage());
      serviceResponse.setErrorCode(ex.getErrorCode());
    } else {
      serviceResponse.setErrorCode(ErrorCode.M001);
      serviceResponse.setMessage("Something went wrong");
      LOGGER.log(Level.INFO, exception.getMessage());
    }
    return new ResponseEntity<>(serviceResponse, status);
  }

}
