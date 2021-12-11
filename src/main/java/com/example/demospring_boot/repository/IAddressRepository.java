package com.example.demospring_boot.repository;



import com.example.demospring_boot.model.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends PagingAndSortingRepository<Address, Long> {
}
