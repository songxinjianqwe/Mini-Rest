package cn.sinjinsong.sample.controller.advice;


import cn.sinjinsong.sample.exception.base.BaseRestException;
import cn.sinjinsong.sample.exception.domain.RestError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(BaseRestException.class)
	public ResponseEntity<RestError> handle(BaseRestException e) {
		return new ResponseEntity<>(new RestError(e.getStatus(), e.getCode(), e.getErrors(), e.getMoreInfoURL()), e.getStatus());
	}
	
}
