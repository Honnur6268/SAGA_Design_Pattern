package nk.honnur.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PURCHASE_ORDERS_TBL")
public class PurchaseOrder {

	@Id
	@GeneratedValue
	private Integer orderId;

	private Integer userId;

	private Integer productId;

	private Double price;

	private String orderStatus;

	private String paymentStatus;

	public PurchaseOrder() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseOrder(Integer orderId, Integer userId, Integer productId, Double price, String orderStatus,
			String paymentStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.price = price;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [orderId=" + orderId + ", userId=" + userId + ", productId=" + productId + ", price="
				+ price + ", orderStatus=" + orderStatus + ", paymentStatus=" + paymentStatus + "]";
	}

}
