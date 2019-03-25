package com.gs.miaosha.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gs.miaosha.service.MiaoshaGoodsService;
import com.gs.miaosha.util.JsonUtil;
import com.gs.miaosha.vo.MiaoshaMessage;

@Service
public class MQReceiver {

	private final Logger logger = LoggerFactory.getLogger(MQReceiver.class);
	
	@Autowired
	private MiaoshaGoodsService miaoshaGoodsService;
	/**
	 * Direct 交换机模式 exchange
	 * @param message
	 */
	@RabbitListener(queues=MqConfig.QUEUE_NAME)
	public void receive(String message) {
		logger.info("receive message："+message);
	}
	
	@RabbitListener(queues=MqConfig.TOPIC_QUEUE1)
	public void topicReceive1(String message) {
		logger.info("receive1 message："+message);
	}
	
	@RabbitListener(queues=MqConfig.TOPIC_QUEUE2)
	public void topicReceive2(String message) {
		logger.info("receive2 message："+message);
	}
	
	@RabbitListener(queues=MqConfig.MIAOSHA_QUEUE)
	public void receiverMiaosha(String message) {
		logger.info("receiverMiaosha message："+message);
		MiaoshaMessage ms = JsonUtil.stringBean(message, MiaoshaMessage.class);
		miaoshaGoodsService.miaosha(ms.getUser(),ms.getMiaoshaGoods());
	}
}
