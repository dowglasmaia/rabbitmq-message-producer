package com.maia.ms.producer.amqp.impl;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.maia.ms.producer.amqp.AmqpProducer;
import com.maia.ms.producer.dto.MessageDTO;


@Component
public class ProducerRabbitMQ implements AmqpProducer<MessageDTO> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;

	@Value("${spring.rabbitmq.routing-key.producer}")
	private String queue;

	@Value("${spring.rabbitmq.exchange.producer}")
	private String exchange;

	@Override
	public void producer(MessageDTO message) {
		try {
			rabbitMessagingTemplate.convertAndSend(exchange, queue, message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	

}
