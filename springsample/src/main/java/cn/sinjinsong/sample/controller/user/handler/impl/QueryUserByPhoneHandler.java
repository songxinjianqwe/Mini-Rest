package cn.sinjinsong.sample.controller.user.handler.impl;

import cn.sinjinsong.sample.controller.user.handler.QueryUserHandler;
import cn.sinjinsong.sample.domain.entity.user.UserDO;
import cn.sinjinsong.sample.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by SinjinSong on 2017/5/6.
 */
@Component("QueryUserHandler.phone")
public class QueryUserByPhoneHandler implements QueryUserHandler {
    @Autowired
    private UserService userService;
    @Override
    public UserDO handle(String key) {
        return userService.findByEmail(key);
    }
}
