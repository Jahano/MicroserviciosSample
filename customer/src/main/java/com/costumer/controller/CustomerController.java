package com.costumer.controller;

import com.costumer.entity.Customer;
import com.costumer.entity.Region;
import com.costumer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomer(@RequestParam(name="regionid", required = false) Long id){
        List<Customer> customerList = new ArrayList<>();
        if(id == null){
            customerList = customerService.listAllProduct();
            if(customerList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            customerList = customerService.findByRegion(Region.builder().id(id).build());
            if(customerList.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(customerList);
    }

}
