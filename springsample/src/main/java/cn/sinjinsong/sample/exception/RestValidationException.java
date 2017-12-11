package cn.sinjinsong.sample.exception;

import cn.sinjinsong.sample.exception.annotation.RestResponseStatus;
import cn.sinjinsong.sample.exception.base.BaseRestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@RestResponseStatus(value= HttpStatus.BAD_REQUEST,code =1)
public class RestValidationException extends BaseRestException {
    public RestValidationException(List<FieldError> errors) {
		super(errors);
	}
}
