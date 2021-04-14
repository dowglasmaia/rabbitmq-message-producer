package com.maia.ms.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maia.ms.producer.dto.MessageDTO;
import com.maia.ms.producer.service.AmqpService;

@RestController
@RequestMapping(value = "/api/v1/messages")
public class AmqpApiController {

	private final AmqpService services;

	@Autowired
	public AmqpApiController(AmqpService services) {
		this.services = services;

	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping("/send")
	public void sendToConsumer(@RequestBody MessageDTO message) {
		services.sendToConsumer(message);
	}

}
