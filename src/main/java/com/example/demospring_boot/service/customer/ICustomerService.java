package com.example.demospring_boot.service.customer;


import com.example.demospring_boot.model.Address;
import com.example.demospring_boot.model.Customer;
import com.example.demospring_boot.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGeneralService<Customer> {
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByFirstName(String firstName, Pageable pageable);
    Page<Customer> findAllByAddress(Address address, Pageable pageable);
    Iterable<Customer> findAllByAddress(Address address);
}
