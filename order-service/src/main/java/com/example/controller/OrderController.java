package com.example.controller;

import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/order/{customerId}/{orderItem}/{amount}")
	public Order getOrder(@PathVariable String orderItem, @PathVariable Float amount, @PathVariable String customerId) {
		log.info("Order received.");
		orderService.processOrder(orderItem, amount, customerId);
		return new Order(String.format("Order for the item %s has been processed with order-id %s", orderItem, UUID.randomUUID()));
	}

	record Order(String orderDesc) {
	}
}