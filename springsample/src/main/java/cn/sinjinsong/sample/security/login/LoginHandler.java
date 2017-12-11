package cn.sinjinsong.sample.security.login;


import cn.sinjinsong.sample.domain.dto.user.LoginDTO;
import cn.sinjinsong.sample.domain.entity.user.UserDO;

/**
 * Created by SinjinSong on 2017/5/7.
 */
public interface LoginHandler {
    UserDO handle(LoginDTO loginDTO);
}
