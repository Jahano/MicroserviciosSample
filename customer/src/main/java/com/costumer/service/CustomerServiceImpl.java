package com.costumer.service;

import com.costumer.entity.Customer;
import com.costumer.entity.Region;
import com.costumer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> listAllProduct() {

        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseGet(null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setState("CREATED");
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDb = customerRepository.findById(customer.getId()).orElseGet(null);
        if(customerDb == null){
            return null;
        }
        customerDb.setNumberId(customer.getNumberId());
        customerDb.setFirstName(customer.getFirstName());
        customerDb.setLastName(customer.getLastName());
        customerDb.setState(customer.getState());
        customerDb.setEmail(customer.getEmail());
        customerDb.setRegion(customer.getRegion());
        customerDb.setPhotoUrl(customer.getPhotoUrl());
        return customerRepository.save(customerDb);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customerDb = customerRepository.findById(id).orElseGet(null);
        if(customerDb == null){
            return null;
        }
        customerDb.setState("DELETED");
        return customerRepository.save(customerDb);
    }

    @Override
    public List<Customer> findByRegion(Region region) {

        return customerRepository.findByRegion(region);
    }
}
