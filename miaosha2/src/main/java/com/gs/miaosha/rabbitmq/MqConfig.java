package com.gs.miaosha.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

@Configuration
public class MqConfig {

	public static final String QUEUE_NAME="queue";
	public static final String TOPIC_QUEUE1="queue1";
	public static final String TOPIC_QUEUE2="queue2";
	//订阅模式
	public static final String TOPIC_EXCHANGE="topicExchange";
	//广播模式
	public static final String FANOUT_EXCHANGE="fanoutExchange";
	
	public static final String MIAOSHA_QUEUE="miaosha.queue";
	
	/**
	 * Direct 交换机模式 exchange
	 * @param message
	 */
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true);
	}
	
	@Bean
	public Queue miaoshaQueue() {
		return new Queue(MIAOSHA_QUEUE, true);
	}
	
	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1, true);
	}
	
	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2, true);
	}
	
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	
	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.key1");
	}
	
	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
	}
	
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}
	
	@Bean
	public Binding fanoutBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
	}
	@Bean
	public Binding fanoutBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
	}
}
