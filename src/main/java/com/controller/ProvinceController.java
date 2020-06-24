package com.controller;

import com.model.Customer;
import com.model.Province;
import com.service.CustomerService;
import com.service.ProvinceService;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/provinces")
    public ModelAndView showListProvince(){
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces",provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province",new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView saveProvince(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province",province);
        modelAndView.addObject("message","New Province created !");
        return modelAndView;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Province province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        if (province!=null){
            modelAndView.addObject("province",province);
            return modelAndView;
        }else {
            modelAndView.addObject("message","Error !");
            return modelAndView;
        }
    }
    @PostMapping("/edit-province")
    public ModelAndView updateProvince(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView =new ModelAndView("/province/edit");
        modelAndView.addObject("province",province);
        modelAndView.addObject("message","Updated province !");
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Province province =provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/province/delete");
        if (province!=null){
            modelAndView.addObject("province",province);
        }else {
            modelAndView.addObject("message","Error !");
        }
        return modelAndView;
    }

    @PostMapping("/delete-province")
    public String  deleteProvince(@ModelAttribute("province") Province province){
        provinceService.remove(province.getId());
        return "redirect:/provinces";
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable Long id){
        Province province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/province/view");
        if (province==null){
            modelAndView.addObject("message","Error !");
        }
        Iterable<Customer> customers = customerService.findAllByProvince(province);
        modelAndView.addObject("customers",customers);
        modelAndView.addObject("province",province);
        return modelAndView;
    }
}
