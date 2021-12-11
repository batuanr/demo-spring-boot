package com.example.demospring_boot.service.customer;


import com.example.demospring_boot.model.Address;
import com.example.demospring_boot.model.Customer;
import com.example.demospring_boot.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllByFirstName(String firstName, Pageable pageable) {
        return customerRepository.findAllByNameContaining(firstName, pageable);
    }

    @Override
    public Page<Customer> findAllByAddress(Address address, Pageable pageable) {
        return customerRepository.findAllByAddress(address, pageable);
    }

    @Override
    public Iterable<Customer> findAllByAddress(Address address) {
        return customerRepository.findAllByAddress(address);
    }
}
