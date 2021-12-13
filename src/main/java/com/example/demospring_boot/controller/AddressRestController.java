package com.example.demospring_boot.controller;


import com.example.demospring_boot.model.Address;
import com.example.demospring_boot.service.address.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/address")
public class AddressRestController {
    @Autowired
    IAddressService addressService;

    @GetMapping
    public ResponseEntity<Iterable<Address>> findAll(){
        return new ResponseEntity<>(addressService.findAll(), HttpStatus.OK);
    }
    
}
