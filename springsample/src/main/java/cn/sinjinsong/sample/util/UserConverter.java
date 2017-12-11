package cn.sinjinsong.sample.util;

import cn.sinjinsong.sample.security.domain.JWTUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

/**
 * Created by SinjinSong on 2017/7/28.
 */
public class UserConverter {
    public static JWTUser convertToUser(Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        return (JWTUser) token.getPrincipal();
    }
}
