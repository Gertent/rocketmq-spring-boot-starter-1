/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.thierrysquirrel.annotation;

import com.aliyun.openservices.ons.api.SendCallback;
import com.github.thierrysquirrel.core.producer.DefaultSendCallback;
import com.github.thierrysquirrel.core.producer.MessageSendType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: CommonMessage
 * Description:
 * date: 2019/5/3 11:16
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CommonMessage {
	/**
	 * Message 所属的 Topic
	 *
	 * @return String
	 */
	String topic() default "";

	/**
	 * 订阅指定 Topic 下的 Tags：
	 * 1. * 表示订阅所有消息
	 * 2. TagA || TagB || TagC 表示订阅 TagA 或 TagB 或 TagC 的消息
	 *
	 * @return String
	 */
	String tag() default "*";

	/**
	 * 重点：配合时间单位投递
	 * 定时消息，单位毫秒（ms），在指定时间戳（当前时间之后）进行投递，例如 2016-03-07 16:21:00 投递。如果被设置成当前时间戳之前的某个时刻，消息将立刻投递给消费者。
	 * 延时消息，单位毫秒（ms），在指定延迟时间（当前时间之后）进行投递，例如消息在 3 秒后投递
	 *
	 * @return long
	 */
	long startDeliverTime() default 0;

	/**
	 * 时间单位  默认秒
	 *
	 * @return TimeUnit
	 */
	TimeUnit timeUnit() default TimeUnit.SECONDS;

	/**
	 * 消息发送类型 默认异步
	 *
	 * @return MessageSendType
	 */
	MessageSendType messageSendType() default MessageSendType.SEND_ASYNC;

	/**
	 * 自定义SendCallback类
	 *
	 * @return callback
	 */
	Class<? extends SendCallback> callback() default DefaultSendCallback.class;
}
