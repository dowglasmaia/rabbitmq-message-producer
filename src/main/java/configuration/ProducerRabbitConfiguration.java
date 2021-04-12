package configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerRabbitConfiguration {

	@Value("${spring.rabbitmq.request.routing-key.producer}")
	private String queue;

	@Value("${spring.rabbitmq.request.exchange.producer}")
	private String exchange;

	@Value("${spring.rabbitmq.request.deadletter.producer}")
	private String deadletter;

	@Bean
	private DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	private Queue deadletter() {
		return new Queue(deadletter);
	}

	@Bean
	private Queue queue() {
		Map<String, Object> args = new HashMap<>();

		args.put("x-dead-letter-exchange", exchange);
		args.put("x-dead-letter-routinh-key", deadletter);

		return new Queue(queue, true, false, false, args);
	}
	
	
	@Bean
	public Binding bindingQueue() {
		return BindingBuilder
				.bind(queue())
				.to(exchange())
				.with(queue);
	}
	
	@Bean
	public Binding bindingDeadletter() {
		return BindingBuilder
				.bind(deadletter())
				.to(exchange())
				.with(deadletter);
	}

}
