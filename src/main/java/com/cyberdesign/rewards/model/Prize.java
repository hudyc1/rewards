package com.cyberdesign.rewards.model;

import java.util.UUID;

import org.joda.time.DateTime;

public class Prize {
	String prizeId;
	String name;
	String descripction;
	int points;
	DateTime startDate;
	DateTime endDate;

	public Prize(String name, String descripction, int points,
			DateTime startDate, DateTime endDate) {
		super();
		prizeId = UUID.randomUUID().toString();
		this.name = name;
		this.descripction = descripction;
		this.points = points;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getPrizeId() {
		return prizeId;
	}

	public void setPrizeId() {
		prizeId = UUID.randomUUID().toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripction() {
		return descripction;
	}

	public void setDescripction(String descripction) {
		this.descripction = descripction;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
}
