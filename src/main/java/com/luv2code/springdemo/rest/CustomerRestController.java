package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // Autowire the customer service which we will use to interact with the database
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {

        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer customer(@PathVariable int customerId) {
        // if customer is not found then hibernate will return null and jackson will convert to json and send
        // empty response for null so we need to handle that
        Customer theCustomer = customerService.getCustomer(customerId);
        if (theCustomer == null) {
            throw new CustomerNotFoundException("Customer not found with the id: " + customerId);
        }
        return theCustomer;
    }

    // add mapping for POST
    @PostMapping("/customers")
    public Customer addNewCustomer(@RequestBody Customer customer){
        // take the request body and put it in customer object right next to the annotation

        // set the customer.setId(0) because backend DAO use hibernate and it makes uese of
        // session.saveOrUpdate() , the way hibernate saveOrUpdate() method works basically it checks
        // the primary key or the id is empty or 0, then it will actually perform an insert on the
        // new customer else it will perform an update on the existing customer.
         customer.setId(0);

         customerService.saveCustomer(customer);

         return customer;
    }

    // add putMapping to update existing customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){

        // since customer id is set DAO will update the customer in the database
        customerService.saveCustomer(customer);

        return customer;
    }
}
