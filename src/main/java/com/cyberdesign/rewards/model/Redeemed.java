package com.cyberdesign.rewards.model;

import java.util.UUID;

import org.joda.time.DateTime;

public class Redeemed {
	String redemptionId;
	String customerId;
	String prizeId;
	DateTime redemptionDate;

	public Redeemed(String customerId, String prizeId, DateTime redemptionDate) {
		super();
		redemptionId = UUID.randomUUID().toString();
		this.customerId = customerId;
		this.prizeId = prizeId;
		this.redemptionDate = redemptionDate;
	}

	public String getRedemptionId() {
		return redemptionId;
	}

	public void setRedemptionId() {
		redemptionId = UUID.randomUUID().toString();

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}

	public DateTime getRedemptionDate() {
		return redemptionDate;
	}

	public void setRedemptionDate(DateTime redemptionDate) {
		this.redemptionDate = redemptionDate;
	}

}
