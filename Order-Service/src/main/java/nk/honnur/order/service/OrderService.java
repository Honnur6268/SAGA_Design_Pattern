package nk.honnur.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import nk.honnur.dto.OrderRequestDto;
import nk.honnur.dto.OrderStatus;
import nk.honnur.dto.PaymentStatus;
import nk.honnur.entity.PurchaseOrder;
import nk.honnur.entity.PurchaseOrderPayments;
import nk.honnur.repo.PurchaseOrderRepository;

@Service
public class OrderService {

	@Autowired
	private PurchaseOrderRepository orderRepo;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Transactional(rollbackOn = { Exception.class, RuntimeException.class })
	public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {

		// create order in database
		PurchaseOrder purchaseOrder = orderRepo.save(convertDtoToEntity(orderRequestDto));

		orderRequestDto.setOrderId(purchaseOrder.getOrderId());
		orderRequestDto.setOrderStatus(OrderStatus.ORDER_CREATED.toString());

//		int i = 10/0;

		// publish order creation msg to kafka topic
		kafkaTemplate.send("orders-topic", purchaseOrder);
		return purchaseOrder;

	}

	public List<PurchaseOrder> getAllOrders() {
		return orderRepo.findAll();
	}

	public PurchaseOrder convertDtoToEntity(OrderRequestDto dto) {
		PurchaseOrder entity = new PurchaseOrder();
		entity.setProductId(dto.getProductId());
		entity.setUserId(dto.getUserId());
		entity.setOrderStatus(OrderStatus.ORDER_CREATED.toString());
		entity.setPrice(dto.getAmount());
		return entity;
	}

	// listening to payments topic to confirm/cancel order
	@KafkaListener(topics = "payments-topic", groupId = "honnu-group")
	public void handleOrderStatusUpdate(PurchaseOrderPayments orderPayment) {
		String paymentStatus = orderPayment.getPaymentStatus();
		orderRepo.findById(orderPayment.getOrderId()).ifPresent(po -> {
			if (paymentStatus.equals(PaymentStatus.PAYMENT_COMPLETED.toString())) {
				po.setOrderStatus(OrderStatus.ORDER_COMPLETED.toString());
			} else {
				po.setOrderStatus(OrderStatus.ORDER_FAILED.toString());
			}
			po.setPaymentStatus(orderPayment.getPaymentStatus());
			orderRepo.save(po); // updating order status
		});
	}

	public OrderRequestDto convertEntityToDto(PurchaseOrder order) {
		OrderRequestDto dto = new OrderRequestDto();
		dto.setOrderId(order.getOrderId());
		dto.setUserId(order.getUserId());
		dto.setAmount(order.getPrice());
		dto.setProductId(order.getProductId());
		return dto;
	}
}
