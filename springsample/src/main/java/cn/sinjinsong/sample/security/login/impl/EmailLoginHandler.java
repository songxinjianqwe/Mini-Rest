package cn.sinjinsong.sample.security.login.impl;

import cn.sinjinsong.sample.domain.dto.user.LoginDTO;
import cn.sinjinsong.sample.domain.entity.user.UserDO;
import cn.sinjinsong.sample.security.login.LoginHandler;
import cn.sinjinsong.sample.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by SinjinSong on 2017/5/6.
 */
@Component("LoginHandler.email")
public class EmailLoginHandler implements LoginHandler {
    @Autowired
    private UserService service;
    
    @Override
    public UserDO handle(LoginDTO loginDTO) {
        return service.findByEmail(loginDTO.getEmail());
    }
}
