package com.controller;

import com.model.Customer;
import com.model.Province;
import com.service.CustomerService;
import com.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("/provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

//    @GetMapping("/customers")
//    public ModelAndView showList(){
//        Iterable<Customer> customers = customerService.findAll();
//        ModelAndView modelAndView = new ModelAndView("/customer/list");
//        modelAndView.addObject("customers",customers);
//        return modelAndView;
//    }

//    @GetMapping("/customers")
//    public ModelAndView listCustomers(Pageable pageable){
//        Page<Customer> customers = customerService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("/customer/list");
//        modelAndView.addObject("customers",customers);
//        return modelAndView;
//    }

    @GetMapping("/customers")
    public ModelAndView listCustomers(@RequestParam("s")Optional<String> s, @PageableDefault(size = 5) Pageable pageable){
        Page<Customer> customers;
        if (s.isPresent()){
            customers = customerService.findAllByFirstNameContaining(s.get(),pageable);
        }else {
            customers =customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("provinces",provinceService.findAll());
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("provinces",provinceService.findAll());
        modelAndView.addObject("message","Add Customer successfully !");
        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Customer customer = customerService.finById(id);
        if (customer!=null){
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("message","Edit Error !");
            return modelAndView;
        }
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","Customer updated successfully !");
        return modelAndView;
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Customer customer = customerService.finById(id);
        if (customer!=null){
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("message","Error !");
            return modelAndView;
        }
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:/customers";
    }
}
