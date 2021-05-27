package com.WebDatabaseApplication.controller;

import com.WebDatabaseApplication.entity.Product;
import com.WebDatabaseApplication.entity.User;
import com.WebDatabaseApplication.repository.ProductRepo;
 import com.WebDatabaseApplication.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@Controller
public class MainController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home (){
        return "index";
    }

    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @GetMapping("/main")
    public String main(Model model ,Principal principal) {
        Iterable<Product> products = productRepo.findAllByStatus("for sale");
        if (userService.isAdmin(principal.getName())){
            model.addAttribute("isAdmin", true);
        }
        model.addAttribute("products", products);
        return "/main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String name,
                      @RequestParam String modelProduct,
                      @RequestParam String type,
                       Model model) {
        Product product = new Product(name, modelProduct,type,"new in your inventory",user);
        productRepo.save(product);

        Iterable<Product> products = productRepo.findAll();

        model.addAttribute("products", products);
        return "redirect:/inventory";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model,Principal principal) {
        Iterable<Product> product;

        if (filter != null && !filter.isEmpty()) {
            product = productRepo.findByType(filter);
        } else {
            product = productRepo.findAllByStatus("for sale");
        }
        if (userService.isAdmin(principal.getName())){
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("products", product);

        return "main";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model){
        productRepo.deleteById(id);
        model.addAttribute("products", productRepo.findAll());
        return "redirect:/main";
    }
    @RequestMapping("/temp")
    public String temp(
            @RequestParam("id") Long id,
            Model model
    ) {
        model.addAttribute("products", productRepo.findById(id));
        return "edit";
    }
    @RequestMapping("/edit")
    public String update(
            @RequestParam("id") Long id,
            @RequestParam String name,
            @RequestParam String modelProduct,
            @RequestParam String type,
            @RequestParam("authorId") User user,
            Model model) {
        Product product = new Product(id, name,modelProduct,type, user, "for sale");
        productRepo.save(product);
        model.addAttribute("products",  productRepo.findAll());
        return "redirect:/main";
    }
}