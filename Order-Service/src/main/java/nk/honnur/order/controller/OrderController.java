package nk.honnur.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nk.honnur.dto.OrderRequestDto;
import nk.honnur.entity.PurchaseOrder;
import nk.honnur.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {
		return orderService.createOrder(orderRequestDto);
	}

	@GetMapping("/orders")
	public List<PurchaseOrder> getAllOrders() {
		return orderService.getAllOrders();
	}

}
