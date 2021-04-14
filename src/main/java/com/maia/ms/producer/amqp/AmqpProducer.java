package com.maia.ms.producer.amqp;

import java.io.Serializable;

public interface AmqpProducer<T> extends Serializable {
	
	void producer(T message);

}
