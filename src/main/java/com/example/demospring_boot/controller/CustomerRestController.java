package com.example.demospring_boot.controller;

import com.example.demospring_boot.model.Address;
import com.example.demospring_boot.model.Customer;
import com.example.demospring_boot.service.address.IAddressService;
import com.example.demospring_boot.service.customer.ICustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerRestController {

    @Autowired
    Environment env;
    @Autowired
    ICustomerService customerService;

    @Autowired
    IAddressService addressService;
    @ModelAttribute("addressList")
    public Iterable<Address> getListAddress(){
        return addressService.findAll();
    }
    @GetMapping("/home")
    public ModelAndView listCustomers(Optional<String> search, @PageableDefault(value = 5) Pageable pageable){
        Page<Customer> customers;
        if(search.isPresent()){
            customers = customerService.findAllByFirstName(search.get(), pageable);
        }

        else {
            customers = customerService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<Iterable<Customer>> findAll(){
//        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(@PageableDefault(value = 5) Pageable pageable){
        return new ResponseEntity<>(customerService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<Customer> saveCustomer(@RequestPart("file") MultipartFile file, @RequestPart("customer") String adString ) {
//        MultipartFile multipartFile = file;
        if (!file.isEmpty()){
            String file1 = file.getOriginalFilename();
            try {
                Customer customer = new ObjectMapper().readValue(adString, Customer.class);
                customer.setImage(file1);
                customerService.save(customer);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            String fileUpload = env.getProperty("upload.path");
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + file1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Customer customer = new ObjectMapper().readValue(adString, Customer.class);
                customerService.save(customer);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

//        Customer customer = new Customer(customerForm.getName(), customerForm.getEmail(), customerForm.getAddress(), file1);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional.get().getId());
        customerService.save(customer);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.remove(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }
    @GetMapping("/findByAddress/{id}")
    public ResponseEntity<Iterable<Customer>> findCustomerByAddress(@PathVariable Long id, Pageable pageable){
        Optional<Address> address = addressService.findById(id);

        return new ResponseEntity<>(customerService.findAllByAddress(address.get()) ,HttpStatus.OK);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Customer> findOne(@PathVariable Long id){
        return new ResponseEntity<>(customerService.findById(id).get(), HttpStatus.OK);
    }
}
