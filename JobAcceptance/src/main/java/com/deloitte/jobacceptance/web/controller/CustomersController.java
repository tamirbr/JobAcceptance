package com.deloitte.jobacceptance.web.controller;

import com.deloitte.jobacceptance.model.Customer;
import com.deloitte.jobacceptance.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/customers/")
@Api(value = "CustomersControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomersController {
    @Autowired
    private CustomerService customerService;

    // Find all customers
    @RequestMapping(path = "customers", method = RequestMethod.GET)
    @ApiOperation("Get all customers")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Customer.class)})
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    // Finds customer
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the customer with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Customer.class)})
    public Customer getCustomer(@PathVariable(name = "id") String id) {
        return customerService.findById(Long.parseLong(id));
    }

    // Saves new customer
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Saves new customer")
    public void saveCustomer(@RequestBody Customer customerToSave) {
        customerService.save(customerToSave);
    }

    // Edit customer
    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Edit customer by id")
    public void updateCustomer(@RequestBody Customer customerToUpdate, @PathVariable(name = "id") String id) {
        customerToUpdate.setId(Long.parseLong(id));
        customerService.save(customerToUpdate);
    }

    // Removes customer
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    @ApiOperation("Removes customer by id")
    public void deleteCustomer(@PathVariable(name = "id") String id) {
        customerService.delete(Long.parseLong(id));
    }
}
