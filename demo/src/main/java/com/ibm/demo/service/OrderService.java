package com.ibm.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.ibm.demo.entity.Order;
import com.ibm.demo.repo.OrderRepository;


@Service
public class OrderService { // Spring Beans
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	RestTemplate getTaxesTemplet;
	String uriVariable;
	public String createOrder(@RequestBody Order order) {
		//call getTaxes
		Float response = getTaxesTemplet.getForObject("http://localhost:8080/getTaxes?price=(price)", Float.class, uriVariable);
		System.out.println(response);
		Order savedOrder = orderRepository.save(order);
		return savedOrder.getId();
	}

	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	public void deleteOrder(String orderId) {
		orderRepository.deleteById(orderId);
	}

	public Optional<Order> getOrder(String orderId){
		return orderRepository.findById(orderId);
	}
}