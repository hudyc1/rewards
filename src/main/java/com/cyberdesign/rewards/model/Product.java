package com.cyberdesign.rewards.model;

import java.util.Date;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Product {
	String productId;
	String name;
	String description;
	int points;
	DateTime effectiveStart;
	DateTime effectiveEnd;
	Date startDate;
	Date endDate;

	DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

	public Product(String name) {
		super();
		productId = UUID.randomUUID().toString();
		this.name = name;
	}

	public Product(String name, String description, int points,
			DateTime effectiveStart, DateTime effectiveEnd) {
		super();
		productId = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.points = points;
		this.effectiveStart = effectiveStart;
		this.effectiveEnd = effectiveEnd;
		this.startDate = effectiveStart.toDate();
		this.endDate = effectiveEnd.toDate();
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductId() {
		productId = UUID.randomUUID().toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public DateTime getEffectiveStart() {
		return effectiveStart;
	}

	public void setEffectiveStart(DateTime effectiveStart) {
		this.effectiveStart = effectiveStart;
		this.startDate = effectiveStart.toDate();
	}

	public DateTime getEffectiveEnd() {
		return effectiveEnd;
	}

	public void setEffectiveEnd(DateTime effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
		this.endDate = effectiveEnd.toDate();
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	/*public void setStartDate(Date startDate) {
		this.effectiveStart = new DateTime(startDate);
		this.startDate = effectiveStart.toDate();

	}

	public void setEndDate(Date endDate) {
		this.effectiveEnd = new DateTime(endDate);
		this.endDate = effectiveEnd.toDate();
	}*/

	public void setStartDate(String startDate) {
		
		this.effectiveStart = new DateTime(startDate);
		this.startDate = effectiveStart.toDate();
	}

	public void setEndDate(String endDate) {
		this.effectiveEnd = new DateTime(endDate);
		this.endDate = effectiveEnd.toDate();
	}

}
