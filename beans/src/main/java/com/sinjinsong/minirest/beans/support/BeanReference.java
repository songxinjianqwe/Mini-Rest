package com.sinjinsong.minirest.beans.support;

import java.util.Objects;

/**
 * @author sinjinsong
 * @date 2018/3/4
 * 对应着配置文件中的bean ref
 */
public class BeanReference {
    private final String beanName;
    private Object source;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
    
    
    public String getBeanName() {
		return this.beanName;
	}

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanReference that = (BeanReference) o;
        return Objects.equals(beanName, that.beanName) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {

        return Objects.hash(beanName, source);
    }

    @Override
    public String toString() {
        return "BeanReference{" +
                "beanName='" + beanName + '\'' +
                ", source=" + source +
                '}';
    }
}
