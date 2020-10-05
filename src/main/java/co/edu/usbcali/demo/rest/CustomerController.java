package co.edu.usbcali.demo.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.dto.CustomerDTO;
import co.edu.usbcali.demo.mapper.CustomerMapper;
import co.edu.usbcali.demo.repository.CustomerRepository;
import co.edu.usbcali.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	private final static Logger log=LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
	
	@Autowired
	CustomerMapper customerMapper;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody CustomerDTO customerDTO) throws Exception{		
		Customer customer=customerMapper.toCustomer(customerDTO);
		customer=customerService.save(customer);
		customerDTO=customerMapper.toCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody CustomerDTO customerDTO) throws Exception{		
		Customer customer=customerMapper.toCustomer(customerDTO);
		customer=customerService.update(customer);
		customerDTO=customerMapper.toCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
		
	}
	
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
		List<Customer> customers=customerService.findAll();
		List<CustomerDTO> customerDTOs=customerMapper.toCustomersDTO(customers);
		return ResponseEntity.ok().body(customerDTOs);	
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<?> delete(@PathVariable("email") String email) throws Exception {
		
		customerService.deleteById(email);
		
		return ResponseEntity.ok().build();			
	}
	
	
	@GetMapping("/findById/{email}")
	public ResponseEntity<?> findById(@PathVariable("email") String email) throws Exception {
		
		Optional<Customer> customerOptional = customerService.findById(email);
		if(customerOptional.isPresent()==false) {
			return ResponseEntity.ok().body(null);
		}	
		
		Customer customer= customerOptional.get();
		//paso la info del entity al dto
		CustomerDTO customerDTO= customerMapper.toCustomerDTO(customer);
		
		return ResponseEntity.ok().body(customerDTO);			
	}
	
}
 