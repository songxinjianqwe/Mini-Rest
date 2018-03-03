package com.sinjinsong.minirest.beans.support;

/**
 * @author sinjinsong
 * @date 2018/3/3
 * BeanDefinition中的属性键值对
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
