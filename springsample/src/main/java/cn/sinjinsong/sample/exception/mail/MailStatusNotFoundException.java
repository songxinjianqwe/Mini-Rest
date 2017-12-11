package cn.sinjinsong.sample.exception.mail;

import cn.sinjinsong.sample.exception.annotation.RestField;
import cn.sinjinsong.sample.exception.annotation.RestResponseStatus;
import cn.sinjinsong.sample.exception.base.BaseRestException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/5.
 */
@RestResponseStatus(value= HttpStatus.NOT_FOUND,code=7)
@RestField("targetId")
public class MailStatusNotFoundException extends BaseRestException {
    public MailStatusNotFoundException(Long targetId){
        super(targetId);
    }
}
