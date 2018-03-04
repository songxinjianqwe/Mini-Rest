package com.sinjinsong.minirest.beans.exception;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class BeansException extends RuntimeException {
    public BeansException(String msg) {
		super(msg);
	}
    
    public BeansException(String msg, Throwable cause) {
        super(msg);
        initCause(cause);
    }
}
