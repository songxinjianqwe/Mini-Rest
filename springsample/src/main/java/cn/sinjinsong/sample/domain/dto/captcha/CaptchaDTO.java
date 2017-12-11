package cn.sinjinsong.sample.domain.dto.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaDTO {
    private String image;
    private String value;
}
