package com.cyberdesign.rewards.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyberdesign.rewards.model.Address;
import com.cyberdesign.rewards.model.Customer;

@RestController
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerController.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/addcustomer", method = RequestMethod.GET)
	public Customer addCustomer(
			@RequestParam(value = "fname") String fname,
			@RequestParam(value = "lname") String lname,
			@RequestParam(value = "addressLine1", required = false) String addressLine1,
			@RequestParam(value = "addressLine2", required = false) String addressLine2,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "country", defaultValue = "USA") String country,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "homePhone", required = false) String homePhone,
			@RequestParam(value = "cell", required = false) String cell) {

		LOGGER.info("Inserting customer " + fname + " " + lname + " " + country);

		Address a = new Address(addressLine1, addressLine2, city, state, zip,
				country);
		Customer c = new Customer(fname, lname, a, email, homePhone, cell);
		jdbcTemplate
				.update("Insert into customer (customerid, fname, lname, adrs1,adrs2,city,state,zip,country,email,homephone,cell) values (?,?,?,?,?,?,?,?,?,?,?,?)",
						new Object[] { c.getCustomerId(), c.getFirstName(),
								c.getLastName(),
								c.getAddress().getAddressLine1(),
								c.getAddress().getAddressLine2(),
								c.getAddress().getCity(),
								c.getAddress().getState(),
								c.getAddress().getZip(),
								c.getAddress().getCountry(), c.getEmail(),
								c.getHomePhone(), c.getCell() });
		System.out.println(c.getAddress().getCountry());
		return c;
	}

	@RequestMapping(value = "/viewcustomer", method = RequestMethod.GET)
	public List<Customer> viewCustomer(
			@RequestParam(value = "fname", required = false) String fname,
			@RequestParam(value = "lname", required = false) String lname,
			@RequestParam(value = "addressLine1", required = false) String addressLine1,
			@RequestParam(value = "addressLine2", required = false) String addressLine2,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "homePhone", required = false) String homePhone,
			@RequestParam(value = "cell", required = false) String cell) {

		ArrayList<String[]> fields = new ArrayList<String[]>();
		if (fname != null) {
			fields.add(new String[] { "fname", fname });
		}
		if (lname != null) {
			fields.add(new String[] { "lname", lname });
		}
		if (addressLine1 != null) {
			fields.add(new String[] { "adrs1", addressLine1 });
		}
		if (addressLine2 != null) {
			fields.add(new String[] { "adrs2", addressLine2 });
		}
		if (city != null) {
			fields.add(new String[] { "city", city });
		}
		if (state != null) {
			fields.add(new String[] { "state", state });
		}
		if (zip != null) {
			fields.add(new String[] { "zip", zip });
		}
		if (country != null) {
			fields.add(new String[] { "country", country });
		}
		if (email != null) {
			fields.add(new String[] { "email", email });
		}
		if (homePhone != null) {
			fields.add(new String[] { "homePhone", homePhone });
		}
		if (cell != null) {
			fields.add(new String[] { "cell", cell });
		}

		String field = "select * from customer";
		boolean first = true;
		int i = 0;
		Iterator<String[]> it = fields.iterator();
		Object[] o = new Object[fields.size()];
		while (it.hasNext()) {
			String[] s = it.next();
			if (first) {
				field = "select * from customer where " + s[0] + "=?";
				first = false;
			} else {
				field = field.concat(" AND " + s[0] + "=?");
			}
			o[i] = s[1];
			i++;
			LOGGER.info("Query: " + field);

		}
		return jdbcTemplate.query(field, o,
				new ResultSetExtractor<List<Customer>>() {
					@Override
					public List<Customer> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<Customer> list = new ArrayList<Customer>();
						while (rs.next()) {
							Address a = new Address();
							Customer c = new Customer();
							c.setCustomerId(rs.getString(1));
							c.setFirstName(rs.getString(2));
							c.setLastName(rs.getString(3));
							a.setAddressLine1(rs.getString(4));
							a.setAddressLine2(rs.getString(5));
							a.setCity(rs.getString(6));
							a.setState(rs.getString(7));
							a.setZip(rs.getString(8));
							a.setCountry(rs.getString(9));
							c.setEmail(rs.getString(10));
							c.setHomePhone(rs.getString(11));
							c.setCell(rs.getString(12));

							list.add(c);
						}
						return list;
					}
				});

	}
}
