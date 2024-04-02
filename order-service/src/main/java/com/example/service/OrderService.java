package com.example.service;

import com.example.entity.Order;
import com.example.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	public void processOrder(String orderItem, Float amount, String customerId) {

		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8092/payment?amount=" + amount.floatValue(),
				String.class);

		log.info(response.getBody());

		if (orderRepository.countByOrderItemAndCustomerId(orderItem, customerId) > 0) {
			log.info("Order already exists !!");
		} else {
			Order order = new Order(orderItem, amount, customerId);
			orderRepository.save(order);
			log.info("Order has been processed!!");
		}
	}
}