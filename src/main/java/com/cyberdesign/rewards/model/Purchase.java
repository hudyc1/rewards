package com.cyberdesign.rewards.model;

import java.util.UUID;

import org.joda.time.DateTime;

public class Purchase {
	String purchaseId;
	String customerId;
	String productId;
	DateTime purchaseDate;
	int quantity;
	String status;

	public Purchase(String customerId, String productId,
			DateTime purchaseDate, int quantity, String status) {
		super();
		
		purchaseId = UUID.randomUUID().toString();
		this.customerId = customerId;
		this.productId = productId;
		this.purchaseDate = purchaseDate;
		this.quantity = quantity;
		this.status = status;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId() {
		purchaseId = UUID.randomUUID().toString();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public DateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(DateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
