package com.sinjinsong.minirest.sample.service;

/**
 * @author sinjinsong
 * @date 2018/3/5
 */
public class LoginServiceImpl implements LoginService{
    private String initStr;
    private UserService userService;

    public void setInitStr(String initStr) {
        this.initStr = initStr;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void login(String username, String password) {
        System.out.println(initStr);
        System.out.println(username + " login...");
        if(password.equals(userService.findPasswordByUsername(username))){
            System.out.println("login success ...");
        }else{
            throw new RuntimeException("password error");
        }
    }
}
