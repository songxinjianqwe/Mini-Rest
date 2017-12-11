package cn.sinjinsong.sample.exception.user;

import cn.sinjinsong.sample.exception.annotation.RestField;
import cn.sinjinsong.sample.exception.annotation.RestResponseStatus;
import cn.sinjinsong.sample.exception.base.BaseRestException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@RestResponseStatus(value = HttpStatus.NOT_FOUND,code =1)
@RestField("queryMode")
public class QueryUserModeNotFoundException extends BaseRestException {
    public QueryUserModeNotFoundException(String mode){
        super(mode);
    }
}
