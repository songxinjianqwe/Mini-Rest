package com.sinjinsong.minirest.sample.service;

/**
 * @author sinjinsong
 * @date 2018/3/5
 */
public class UserServiceImpl implements UserService{
    
    @Override
    public String findPasswordByUsername(String username) {
        return "12345";
    }
}
