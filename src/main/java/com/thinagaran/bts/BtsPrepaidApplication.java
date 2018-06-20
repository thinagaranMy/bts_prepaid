package com.thinagaran.bts;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BtsPrepaidApplication {

	@Autowired
	private CustomerDAO cdao;
	public static void main(String[] args) {
		SpringApplication.run(BtsPrepaidApplication.class, args);
	}
	@Bean
	public CommandLineRunner run() throws Exception {
		
		return args -> {
			System.out.println("Building new customers");
			Customer customer = new Customer();
			customer.setBalance(new BigDecimal(20));
			customer.setName("Balakumaran Vadivelo");
			cdao.save(customer);
		};
	}
}
