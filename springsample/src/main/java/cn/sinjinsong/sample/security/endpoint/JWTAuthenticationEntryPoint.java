package cn.sinjinsong.sample.security.endpoint;

import cn.sinjinsong.sample.exception.base.BaseRestException;
import cn.sinjinsong.sample.exception.domain.RestError;
import cn.sinjinsong.sample.properties.AuthenticationProperties;
import cn.sinjinsong.sample.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * This is invoked when user tries to access a secured REST resource without supplying any credentials
 */
@Component
@Slf4j
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final String UNAUTHORIZED = JsonUtil.json(new RestError(HttpStatus.UNAUTHORIZED, 1, null, ""));
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.info("到达JWTAuthenticationEntryPoint");
        if(request.getAttribute(AuthenticationProperties.EXCEPTION_ATTR_NAME) != null){
            BaseRestException exception = (BaseRestException) request.getAttribute(AuthenticationProperties.EXCEPTION_ATTR_NAME);
            response.setStatus(exception.getStatus().value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().append(JsonUtil.json(new RestError(exception.getStatus(),exception.getCode(),exception.getErrors(),exception.getMoreInfoURL())));
        }else{
            response.setStatus(401);
            response.getWriter().append(UNAUTHORIZED);
        }
    }
}
