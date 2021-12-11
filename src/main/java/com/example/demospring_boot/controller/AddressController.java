//package com.example.demospring_boot.controller;
//
//import com.model.Address;
//import com.service.address.IAddressService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Optional;
//
//@Controller
//public class AddressController {
//
//    @Autowired
//    private IAddressService addressService;
//
//    @GetMapping("/address")
//    public ModelAndView listProvinces() {
//        Iterable<Address> addresses = addressService.findAll();
//        ModelAndView modelAndView = new ModelAndView("/address/list");
//        modelAndView.addObject("address", addresses);
//        return modelAndView;
//    }
//
//    @GetMapping("/create-province")
//    public ModelAndView showCreateForm() {
//        ModelAndView modelAndView = new ModelAndView("/address/create");
//        modelAndView.addObject("address", new Address());
//        return modelAndView;
//    }
//
//    @PostMapping("/create-province")
//    public ModelAndView saveProvince( Address address) {
//        addressService.save(address);
//
//        ModelAndView modelAndView = new ModelAndView("/address/create");
//        modelAndView.addObject("address", new Address());
//        modelAndView.addObject("message", "New province created successfully");
//        return modelAndView;
//    }
//
//    @GetMapping("/edit-province/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id) {
//        Optional<Address> address = addressService.findById(id);
//        ModelAndView modelAndView;
//        if (address.isPresent()) {
//            modelAndView = new ModelAndView("/address/edit");
//            modelAndView.addObject("address", address.get());
//
//        } else {
//            modelAndView = new ModelAndView("/error.404");
//        }
//        return modelAndView;
//    }
//
//    @PostMapping("/edit-province")
//    public ModelAndView updateProvince(@ModelAttribute("province") Address address) {
//        addressService.save(address);
//        ModelAndView modelAndView = new ModelAndView("/address/edit");
//        modelAndView.addObject("address", address);
//        modelAndView.addObject("message", "Province updated successfully");
//        return modelAndView;
//    }
//
//    @GetMapping("/delete-province/{id}")
//    public ModelAndView showDeleteForm(@PathVariable Long id) {
//        Optional<Address> address = addressService.findById(id);
//        ModelAndView modelAndView;
//        if (address.isPresent()) {
//            modelAndView = new ModelAndView("/address/delete");
//            modelAndView.addObject("address", address.get());
//
//        } else {
//            modelAndView = new ModelAndView("/error.404");
//        }
//        return modelAndView;
//    }
//
//    @PostMapping("/delete-province")
//    public String deleteProvince( Address address) {
//        addressService.remove(address.getId());
//        return "redirect:/address";
//    }
//}
