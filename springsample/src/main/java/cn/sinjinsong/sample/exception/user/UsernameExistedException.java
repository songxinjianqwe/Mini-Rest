package cn.sinjinsong.sample.exception.user;

import cn.sinjinsong.sample.exception.annotation.RestField;
import cn.sinjinsong.sample.exception.annotation.RestResponseStatus;
import cn.sinjinsong.sample.exception.base.BaseRestException;
import org.springframework.http.HttpStatus;

@RestResponseStatus(value = HttpStatus.CONFLICT, code = 1)
@RestField("name")
public class UsernameExistedException extends BaseRestException {
    public UsernameExistedException(String name) {
        super(name);
    }

}
