package com.cyberdesign.rewards.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cyberdesign.rewards.model.Product;

@RestController
// @SpringBootApplication
public class ProductController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProductController.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	public Product addProduct(@RequestBody Product p) {

		p.setProductId();
		if (p.getEffectiveStart() == null) {
			p.setEffectiveStart(DateTime.now());
		}
		if (p.getEffectiveEnd() == null) {
			p.setEffectiveEnd(new DateTime(9999, 12, 31, 0, 0));
		}
		jdbcTemplate
				.update("Insert into product (productId, name, description, points, effectiveStartDate, effectiveEndDate) values (?,?,?,?,?,?)",
						new Object[] { p.getProductId(), p.getName(),
								p.getDescription(), p.getPoints(),
								p.getEffectiveStart().toString(),
								p.getEffectiveEnd().toString()});
		return p;
	}

	@RequestMapping(value = "/viewproduct", method = RequestMethod.GET)
	public List<Product> viewProduct(
			@RequestParam(value = "name", required = false) String name) {
		if (name == null) {
			return jdbcTemplate.query("select * from product",
					new ResultSetExtractor<List<Product>>() {
						@Override
						public List<Product> extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							List<Product> list = new ArrayList<Product>();
							while (rs.next()) {
								Product p = new Product();
								p.setProductId(rs.getString(1));
								p.setName(rs.getString(2));
								p.setDescription(rs.getString(3));
								p.setPoints(rs.getInt(4));
								p.setStartDate(rs.getString(5));
								p.setEndDate(rs.getString(6));
								list.add(p);
							}
							return list;
						}
					});
		} else {
			Object[] o = { name };
			return jdbcTemplate.query("select * from product where name = ?",
					o, new ResultSetExtractor<List<Product>>() {
						@Override
						public List<Product> extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							List<Product> list = new ArrayList<Product>();
							while (rs.next()) {
								Product p = new Product();
								p.setProductId(rs.getString(1));
								p.setName(rs.getString(2));
								p.setDescription(rs.getString(3));
								p.setPoints(rs.getInt(4));
								p.setStartDate(rs.getString(5));
								p.setEndDate(rs.getString(6));
								list.add(p);
							}
							return list;
						}
					});
		}
	}

	@RequestMapping(value = "/deleteproduct/{productId}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("productId") String productId) {

		jdbcTemplate
				.update("DELETE FROM product  WHERE productId=?", productId);
		System.out.println("deleted:" + productId);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/updateproduct/{productId}", method = RequestMethod.PUT, headers = { "Content-type=application/json" })
	public void updateProduct(@PathVariable("productId") String productId, @RequestBody Product p) {
		
		

			p.setProductId(productId);
			if (p.getEffectiveStart() == null) {
				p.setEffectiveStart(DateTime.now());
			}
			if (p.getEffectiveEnd() == null) {
				p.setEffectiveEnd(new DateTime(9999, 12, 31, 0, 0));
			}
			jdbcTemplate
					.update("update product set name=?, description=?, points=?, effectiveStartDate=?, effectiveEndDate=? where productId = ?",
							new Object[] { p.getName(),
									p.getDescription(), p.getPoints(),
									p.getEffectiveStart().toString("yyyy-MM-dd"),
									p.getEffectiveEnd().toString("yyyy-MM-dd"), p.getProductId() });
			
	
		
	}
}



