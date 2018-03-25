/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sinjinsong.minirest.aop.framework;


import com.sinjinsong.minirest.aop.TargetSource;
import com.sinjinsong.minirest.util.ClassUtils;

import java.lang.reflect.Proxy;

/**
 * 用来创建代理的工厂
 */
public class ProxyFactory extends AdvisedSupport {

    public ProxyFactory(TargetSource target) {
        setTargetSource(target);
        setInterfaces(ClassUtils.getAllInterfaces(target));
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (this.isProxyTargetClass()) {
            Class<?> targetClass = this.getTargetClass();
            if (targetClass == null) {
                throw new IllegalStateException("TargetSource cannot determine target class: " +
                        "Either an interface or a target is required for proxy creation.");
            }
            if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
                return new JdkDynamicAopProxy(this);
            }
            return new CglibAopProxy(this);
        } else {
            return new JdkDynamicAopProxy(this);
        }
    }
}
