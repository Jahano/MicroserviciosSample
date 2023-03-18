package com.costumer.service;

import com.costumer.entity.Customer;
import com.costumer.entity.Region;

import java.util.List;

public interface CustomerService {
    public List<Customer> listAllProduct();
    public Customer getCustomer(Long id);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Long id);
    public List<Customer> findByRegion(Region region);
}
