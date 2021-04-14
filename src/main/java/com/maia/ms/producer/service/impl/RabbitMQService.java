package com.maia.ms.producer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.ms.producer.amqp.AmqpProducer;
import com.maia.ms.producer.dto.MessageDTO;
import com.maia.ms.producer.service.AmqpService;

@Service
public class RabbitMQService implements AmqpService{
	
	@Autowired
	private AmqpProducer<MessageDTO> amqp;

	@Override
	public void sendToConsumer(MessageDTO message) {
		amqp.producer(message);
		
	}

}
