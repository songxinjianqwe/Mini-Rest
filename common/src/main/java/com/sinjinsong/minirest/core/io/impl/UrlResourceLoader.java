package com.sinjinsong.minirest.core.io.impl;

import com.sinjinsong.minirest.core.io.Resource;
import com.sinjinsong.minirest.core.io.ResourceLoader;

import java.net.URL;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class UrlResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
