package cn.sinjinsong.sample.domain.dto.user;


import cn.sinjinsong.sample.enumeration.user.UserMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String username;
    private String phone;
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String captchaCode;
    @NotNull
    private String captchaValue;
    @NotNull
    private UserMode userMode;
}
