//package com.example.demospring_boot.controller;
//
//import com.model.Address;
//import com.model.Customer;
//import com.model.CustomerForm;
//import com.service.address.IAddressService;
//import com.service.customer.ICustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.Optional;
//
//@Controller
//public class CustomerController {
//    @Value("${file-upload}")
//    private String fileUpload;
//    @Autowired
//    private ICustomerService customerService;
//
//    @Autowired
//    IAddressService addressService;
//
//    @ModelAttribute("addressList")
//    public Iterable<Address> getListAddress(){
//        return addressService.findAll();
//    }
//
//    @ModelAttribute("customerF")
//    public CustomerForm getCustomerForm(){
//        return new CustomerForm();
//    }
//
//    @GetMapping("/create-customer")
//    public ModelAndView showCreateForm() {
//        ModelAndView modelAndView = new ModelAndView("/customer/create");
//        modelAndView.addObject("customer", new Customer());
//        return modelAndView;
//    }
//
//    @PostMapping("/create-customer")
//    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
//        customerService.save(customer);
//        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
//        redirectAttributes.addFlashAttribute("message", "New customer created successfully");
//        return modelAndView;
//    }
//
//    @GetMapping("/customers")
//    public ModelAndView listCustomers(Optional<String> search,@PageableDefault(value = 5) Pageable pageable){
//        Page<Customer> customers;
//        if(search.isPresent()){
//            customers = customerService.findAllByFirstName(search.get(), pageable);
//        }
//
//        else {
//                customers = customerService.findAll(pageable);
//        }
//
//        ModelAndView modelAndView = new ModelAndView("/customer/home");
//        modelAndView.addObject("customers", customers);
//        return modelAndView;
//    }
//    @PostMapping("/search")
//    public ModelAndView  serchBYAddress(@ModelAttribute Address address, Pageable pageable){
//        Page<Customer> customers = customerService.findAllByAddress(address, pageable);
//        ModelAndView modelAndView = new ModelAndView("/customer/list");
//        modelAndView.addObject("customers", customers);
//        return modelAndView;
//    }
//
//    @GetMapping("/edit-customer/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id) {
//        Optional<Customer> customer = customerService.findById(id);
//        ModelAndView modelAndView;
//        if (customer.isPresent()) {
//            modelAndView = new ModelAndView("/customer/edit");
//            modelAndView.addObject("customer", customer.get());
//        } else {
//            modelAndView = new ModelAndView("/error.404");
//        }
//        return modelAndView;
//    }
//
//    @PostMapping("/edit-customer")
//    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
//        customerService.save(customer);
//        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
//        redirectAttributes.addFlashAttribute("message", "Customer updated successfully");
//        return modelAndView;
//    }
//
//    @GetMapping("/delete-customer/{id}")
//    public ModelAndView showDeleteForm(@PathVariable Long id) {
//        Optional<Customer> customer = customerService.findById(id);
//        ModelAndView modelAndView;
//        if (customer.isPresent()) {
//            modelAndView = new ModelAndView("/customer/delete");
//            modelAndView.addObject("customer", customer.get());
//
//        } else {
//            modelAndView = new ModelAndView("/error.404");
//        }
//        return modelAndView;
//    }
//
//    @PostMapping("/delete-customer")
//    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
//        customerService.remove(customer.getId());
//        return "redirect:customers";
//    }
//}
