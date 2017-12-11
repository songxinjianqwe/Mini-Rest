package cn.sinjinsong.sample.exception.mail;


import cn.sinjinsong.sample.exception.annotation.RestField;
import cn.sinjinsong.sample.exception.annotation.RestResponseStatus;
import cn.sinjinsong.sample.exception.base.BaseRestException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/9.
 */
@RestResponseStatus(value= HttpStatus.NOT_FOUND,code=10)
@RestField("sender")
public class MailReceiverNotFoundException extends BaseRestException {
    public MailReceiverNotFoundException(Long sender){
        super(sender);
    }
}
