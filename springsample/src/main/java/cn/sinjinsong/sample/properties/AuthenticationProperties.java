package cn.sinjinsong.sample.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@Component
@Getter
public class AuthenticationProperties {
    @Value("${token.secretKey}")
    private String secretKey;
    @Value("${token.expireTime}")
    private Integer expireTime;
    @Value("${captcha.expireTime}")
    private Integer captchaExpireTime;
    @Value("${activationCode.expireTime}")
    private Integer activationCodeExpireTime;
    @Value("${forgetNameCode.expireTime}")
    private Integer forgetNameExpireTime;

    public static final String AUTH_HEADER = "Authentication";
    public static final String USER_ID = "id";
    public static final String LOGIN_URL = "/tokens";
    public static final String EXCEPTION_ATTR_NAME = "BaseRestException";
}
