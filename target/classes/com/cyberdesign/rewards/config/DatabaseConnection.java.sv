package com.cyberdesign.rewards.config;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cyberdesign.rewards.model.BadCustomer;

@Component
public class DatabaseConnection implements CommandLineRunner {


	// Retrieve the data source from the application context
//	DataSource ds = (DataSource) ctx.getBean("dataSource");
	Connection c;
	
/*	public void openConnection() {
		// Open a database connection using Spring's DataSourceUtils
		c = DataSourceUtils.getConnection(ds);
	}
	public void closeConnection() {
		DataSourceUtils.releaseConnection(c, ds);
	}*/

	private static final Logger log = LoggerFactory
			.getLogger(DatabaseConnection.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void run(String... strings) throws Exception {

		log.info("Creating tables");
		for (String string : strings) {
			System.out.println(string);
		}
		jdbcTemplate.execute("DROP TABLE customersx IF EXISTS");
		jdbcTemplate
				.execute("CREATE TABLE customersx("
						+ "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays
				.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
				.stream().map(name -> name.split(" "))
				.collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(name -> log.info(String.format(
				"Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate(
				"INSERT INTO customersx(first_name, last_name) VALUES (?,?)",
				splitUpNames);

		log.info("Querying for customer records where first_name = 'Josh':");
		jdbcTemplate
				.query("SELECT id, first_name, last_name FROM customersx WHERE first_name = ? and last_name=?",
						new Object[] { "Josh", "Long" },
						(rs, rowNum) -> new BadCustomer(rs.getLong("id"), rs
								.getString("first_name"), rs
								.getString("last_name"))).forEach(
						customer -> log.info(customer.toString()));
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		
		DatabaseConnection dc = (DatabaseConnection)ctx.getBean("databaseConnection");
		try {
			dc.run(new String[] {"hudy","waz","here"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new DatabaseConnection().openConnection();
		//SpringApplication.run(DatabaseConnection.class, args);
		//new DatabaseConnection().closeConnection();
		
	}

}
