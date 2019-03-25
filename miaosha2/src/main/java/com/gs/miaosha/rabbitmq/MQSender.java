package com.gs.miaosha.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gs.miaosha.util.JsonUtil;
import com.gs.miaosha.vo.MiaoshaMessage;

@Service
public class MQSender {

	private final Logger log = LoggerFactory.getLogger(MQSender.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(Object message) {
		String mes = JsonUtil.beanToString(message);
		log.info(mes);
		amqpTemplate.convertAndSend(MqConfig.QUEUE_NAME,mes);
	}
	
	public void topicSend(Object message) {
		String mes = JsonUtil.beanToString(message);
		log.info(mes);
		amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE,"topic.key1",mes+"1");
		amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE,"topic.#",mes+"2");
	}
	
	public void fanoutSend(Object message) {
		String mes = JsonUtil.beanToString(message);
		log.info(mes);
		amqpTemplate.convertAndSend(MqConfig.FANOUT_EXCHANGE,"",mes);
	}

	public void sendMiaoshaMessage(MiaoshaMessage message) {
		String ms = JsonUtil.beanToString(message);
		log.info("send msg:"+ms);
		amqpTemplate.convertAndSend(MqConfig.MIAOSHA_QUEUE,ms);
	}
}
