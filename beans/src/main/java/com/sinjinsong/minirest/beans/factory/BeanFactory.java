package com.sinjinsong.minirest.beans.factory;

/**
 * 最底层的接口
 */
public interface BeanFactory {
    /**
     * 现在只实现了单例版本
     * @param name
     * @return
     * @throws Exception
     */
	Object getBean(String name) throws Exception;
}
