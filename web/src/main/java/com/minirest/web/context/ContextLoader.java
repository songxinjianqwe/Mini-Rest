package com.minirest.web.context;

import com.minirest.core.io.ClassPathResource;
import com.minirest.core.io.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author songx
 * @date 2017/12/11
 */
public class ContextLoader {
    /**
     * 存放ContextLoader配置信息的文件
     */
    private static final String DEFAULT_STRATEGIES_PATH = "ContextLoader.properties";
    /**
     * 配置文件对象
     */
    private static final Properties defaultStrategies;
	
    static {
		try {
			ClassPathResource resource = new ClassPathResource(DEFAULT_STRATEGIES_PATH, ContextLoader.class);
			defaultStrategies = PropertiesLoaderUtils.loadProperties(resource);
		}
		catch (IOException ex) {
			throw new IllegalStateException("Could not load 'ContextLoader.properties': " + ex.getMessage());
		}
	}
	public ContextLoader(){}
	public ContextLoader(WebApplicationContext context){
        
    }
    
}
