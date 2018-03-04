/*
 * Copyright 2002-2017 the original author or authors.
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

package com.sinjinsong.minirest.aop;

import org.aopalliance.aop.Advice;

/**
 * 
 * 通知器对象可以获取一个通知对象 Advice 。就是用于实现 具体的方法拦截，需要使用者编写，
 * 也就对应了 Spring 中的前置通知、后置通知、环切通知等。
 */
public interface Advisor {
    Advice getAdvice();
}
