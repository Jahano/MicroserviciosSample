package com.costumer;

import com.costumer.entity.Customer;
import com.costumer.entity.Region;
import com.costumer.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CustomerRepositoryMockTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByRegion_thenReturnListOfCustomer(){
        Customer customer = Customer.builder()
                .numberId("123456789")
                .firstName("Alex")
                .lastName("Escutia")
                .email("juanes@gmail.com")
                .photoUrl("")
                .region(Region.builder().id(1L).build())
                .state("CREATED")
                .build();

        customerRepository.save(customer);
        List<Customer> customerList = customerRepository.findByRegion(Region.builder().id(1L).build());
        Assertions.assertThat(customerList.size()).isEqualTo(2);
    }
}
