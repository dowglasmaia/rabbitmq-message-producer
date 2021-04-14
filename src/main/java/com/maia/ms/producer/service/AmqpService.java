package com.maia.ms.producer.service;

import com.maia.ms.producer.dto.MessageDTO;

public interface AmqpService {
	
	void sendToConsumer(MessageDTO message);

}
