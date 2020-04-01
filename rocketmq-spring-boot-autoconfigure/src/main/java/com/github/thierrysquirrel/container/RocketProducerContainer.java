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

package com.github.thierrysquirrel.container;

import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.factory.ThreadPoolFactory;
import com.github.thierrysquirrel.core.strategy.RocketConsumerStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: RocketProducerContainer
 * Description:
 * date: 2019/5/3 11:29
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class RocketProducerContainer implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	private RocketProperties rocketProperties;
	private Map<String, Object> consumerContainer;

	public RocketProducerContainer(Map<String, Object> consumerContainer, RocketProperties rocketProperties) {
		System.out.println("ProducerContainer生产者容器实例化......");
		this.consumerContainer = consumerContainer;
		this.rocketProperties = rocketProperties;
	}

	@PostConstruct
	public void initialize() {
		System.out.println("ProducerContainer生产者容器初始化before......");
		ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.createProducerThreadPoolExecutor(rocketProperties);
		//获取标记为生产者的类
		Map<String, Object> map = applicationContext.getBeansWithAnnotation(RocketMessage.class);
		//遍历生产者类中发送消息的方法
		map.forEach((beanName, bean) -> RocketConsumerStrategy.putProducer(threadPoolExecutor, consumerContainer, bean, rocketProperties, applicationContext));
		threadPoolExecutor.shutdown();


//		applicationContext.getBeansWithAnnotation(RocketMessage.class).forEach((beanName, bean) -> RocketConsumerStrategy.putProducer(threadPoolExecutor, consumerContainer, bean, rocketProperties, applicationContext));
//		threadPoolExecutor.shutdown();
	}


	@Override
	public void setApplicationContext(@Nonnull ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
