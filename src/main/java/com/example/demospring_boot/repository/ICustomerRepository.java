package com.example.demospring_boot.repository;


import com.example.demospring_boot.model.Address;
import com.example.demospring_boot.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Page<Customer> findAllByNameContaining(String firstName, Pageable pageable);
    Page<Customer> findAllByAddress(Address address, Pageable pageable);
    Iterable<Customer> findAllByAddress(Address address);
}
