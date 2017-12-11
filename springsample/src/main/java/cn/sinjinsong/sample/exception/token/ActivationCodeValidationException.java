package cn.sinjinsong.sample.exception.token;

import cn.sinjinsong.sample.exception.annotation.RestField;
import cn.sinjinsong.sample.exception.annotation.RestResponseStatus;
import cn.sinjinsong.sample.exception.base.BaseRestException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/4/28.
 */
@RestResponseStatus(value = HttpStatus.UNAUTHORIZED,code =3)
@RestField("activationCode")
public class ActivationCodeValidationException extends BaseRestException {
    public ActivationCodeValidationException(String activationCode){
        super(activationCode);
    }
}
