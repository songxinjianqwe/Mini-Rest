package cn.sinjinsong.sample.exception.token;

import cn.sinjinsong.sample.exception.annotation.RestField;
import cn.sinjinsong.sample.exception.annotation.RestResponseStatus;
import cn.sinjinsong.sample.exception.base.BaseRestException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/7.
 */
@RestResponseStatus(value= HttpStatus.UNAUTHORIZED,code=4)
@RestField("tokenStatus")
public class TokenStateInvalidException extends BaseRestException {
    public TokenStateInvalidException(String tokenStatus){
        super(tokenStatus);
    }
}
