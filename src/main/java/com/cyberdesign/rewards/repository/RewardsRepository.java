package com.cyberdesign.rewards.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RewardsRepository {
	@Autowired JdbcTemplate jdbcTemplate;

}
