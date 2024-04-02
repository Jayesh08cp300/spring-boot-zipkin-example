package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

	@GetMapping("/payment")
	public Payment getOrder(@RequestParam float amount) {
		log.info("Payment received.");
		return new Payment(String.format("Payment done %s!", amount));
	}

	record Payment(String paymentDesc) {
	}
}