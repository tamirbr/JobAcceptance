package com.deloitte.jobacceptance.web.controller;

import com.deloitte.jobacceptance.model.Customer;
import com.deloitte.jobacceptance.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private CustomerService customerService;

    // Main page
    @RequestMapping("/")
    public String mainPage(Model model) {
        if(!model.containsAttribute("customersList")) {
            model.addAttribute("customersList",customerService.findAll());
        }
        if(!model.containsAttribute("notFound")) {
            model.addAttribute("notFound", false);
        }
        if(!model.containsAttribute("deleted")) {
            model.addAttribute("deleted", false);
        }
        model.addAttribute("search","/search");

        return "main/index";
    }

    // search
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchCustomer(RedirectAttributes redirectAttributes, @RequestParam(value = "customerId") String searchVal) {
        Customer customer = customerService.findById(Long.parseLong(searchVal));
        // Check if customer exist
        if(customer != null){
            // if exist, redirect customer page with customer details
            redirectAttributes.addFlashAttribute("customer",customer);
            return "redirect:/customer/"+customer.getId();
        } else{
            // if not exist, redirect home page with error message
            redirectAttributes.addFlashAttribute("notFound",true);
            return "redirect:/";
        }
    }

    // customer page
    @RequestMapping("/customer/{id}")
    public String customerPage(Model model,@PathVariable Long id) {
        if(!model.containsAttribute("customer")) {
            model.addAttribute("customer", customerService.findById(id));
        }
        model.addAttribute("search","/search");
        model.addAttribute("edit","/customer/"+id);
        model.addAttribute("delete","/delete/"+id);

        return "main/customer";
    }

    // Add new customer page
    @RequestMapping("/add")
    public String addCustomerPage(Model model) {
        if(!model.containsAttribute("customer")) {
            model.addAttribute("customer", new Customer());
        }
        model.addAttribute("search","/search");
        model.addAttribute("add","/add");

        return "main/add";
    }

    // Add new customer post
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCustomer(Model model, @Valid Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

    // edit customer put
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.POST)
    public String editCustomer(Model model, @Valid Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

    // delete customer
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteCustomer(RedirectAttributes redirectAttributes,@PathVariable Long id) {
        customerService.delete(id);
        // notify customer deleted
        redirectAttributes.addFlashAttribute("deleted", true);
        return "redirect:/";
    }
}

