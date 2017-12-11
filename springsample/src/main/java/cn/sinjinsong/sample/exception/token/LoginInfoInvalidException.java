package cn.sinjinsong.sample.exception.token;

import cn.sinjinsong.sample.domain.dto.user.LoginDTO;
import cn.sinjinsong.sample.exception.annotation.RestField;
import cn.sinjinsong.sample.exception.annotation.RestResponseStatus;
import cn.sinjinsong.sample.exception.base.BaseRestException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@RestResponseStatus(value = HttpStatus.UNAUTHORIZED, code = 1)
@RestField("loginInfo")
public class LoginInfoInvalidException extends BaseRestException {
    public LoginInfoInvalidException(LoginDTO loginDTO) {
        super(loginDTO);
    }
}
