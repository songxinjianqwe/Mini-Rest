package com.sinjinsong.minirest.beans.support;

import com.sinjinsong.minirest.beans.exception.BeansException;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
