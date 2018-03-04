package com.sinjinsong.minirest.beans.support;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/3/3
 * 存在一组BeanDefinition的键值对PropertyValue
 */
public class PropertyValues {
    private final List<PropertyValue> list = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        list.add(pv);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : list) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

    public PropertyValue[] getPropertyValues() {
        return list.toArray(new PropertyValue[list.size()]);
    }

    public boolean contains(String propertyName) {
        for (PropertyValue propertyValue : list) {
            if (propertyValue.getName().equals(propertyName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

}
