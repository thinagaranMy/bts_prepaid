package com.thinagaran.bts;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BTSController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("/customers")
	public List<Customer> getUsers(){
		return Lists.newArrayList(customerDAO.findAll());
	}
	
	@RequestMapping("test")
	public String test(){
		return "test";
	}
	
	@PostMapping("/customer")
	public Customer newCustomer(@RequestBody Customer input){
		Customer customer = new Customer();
		customer.setBalance(input.getBalance());
		customer.setName(input.getName());
		return customerDAO.save(customer);
	}
	
	@GetMapping("/customer/{id}")
	public Customer newCustomer(@PathVariable("id") Long id){
		return customerDAO.findById(id).get();
	}
	
	@PutMapping("/customerTopUp/{id}/{value}")
	public Customer topUp(@PathVariable("id") Long id,
			@PathVariable("value") String value ){
		System.out.println("value is"+ value);
		Optional<Customer> oCustomer = customerDAO.findById(id);
		Customer customer =  oCustomer.get();
		customer.setBalance(customer.getBalance().add(new BigDecimal(value)));
		return customerDAO.save(customer);
	}
	@PutMapping("/customerPurchase/{id}/{value}")
	public Customer purchase(@PathVariable("id") Long id,
			@PathVariable("value") String value ){
		System.out.println("value is"+ value);
		Optional<Customer> oCustomer = customerDAO.findById(id);
		Customer customer =  oCustomer.get();
		customer.setBalance(customer.getBalance().subtract(new BigDecimal(value)));
		return customerDAO.save(customer);
	}
}

