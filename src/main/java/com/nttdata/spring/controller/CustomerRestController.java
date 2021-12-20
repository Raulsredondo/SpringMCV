package com.nttdata.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nttdata.spring.repository.NTTDataCustomer;
import com.nttdata.spring.repository.NTTDataCustomerRepository;
import com.nttdata.spring.services.NTTDataCustomerManagementServiceI;

@RestController
@RequestMapping("/")
public class CustomerRestController {

	@Autowired
	private NTTDataCustomerManagementServiceI customerService;
	
	@Autowired
	private NTTDataCustomerRepository customerRepository;
	
	@GetMapping("/customers")
	@ResponseBody
	public String showCustomer() {
		List<NTTDataCustomer> clientes = customerService.searchAllCustomers();
		String clientesJson = new Gson().toJson(clientes);
		return clientesJson;
	}

	@DeleteMapping("/EliminarCustomer/{id}")
	@ResponseBody
	public String deleteCustomer(@PathVariable Long id) {
		customerRepository.deleteById(id);
		List<NTTDataCustomer> clientes = customerService.searchAllCustomers();
		return new Gson().toJson(clientes);
	}
	

	@PostMapping("/addCustomer")
	@ResponseBody
	public String addCustomer(@RequestBody String cliente) {
		NTTDataCustomer cliente1 = new NTTDataCustomer();
		cliente1 = new Gson().fromJson(cliente, NTTDataCustomer.class);
		customerRepository.save(cliente1);
		List<NTTDataCustomer> customers = customerService.searchAllCustomers();
		return new Gson().toJson(customers);
	}
	

	@GetMapping("/ClienteByName/{name}")
	@ResponseBody
	public String getCustomerByName(@PathVariable String name) {
		List<NTTDataCustomer> clientes = customerRepository.findByName(name);
		String clientesJson = new Gson().toJson(clientes);
		return clientesJson;
	}
}
