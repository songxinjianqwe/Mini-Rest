package com.sinjinsong.minirest.beans.support;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/3/3
 * 存在一组BeanDefinition的键值对PropertyValue
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValues = new ArrayList<>();
    
    public void addPropertyValue(PropertyValue pv) {
        propertyValues.add(pv);
    }
    
    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }
}
