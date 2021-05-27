package com.WebDatabaseApplication.controller;

import com.WebDatabaseApplication.entity.Product;
import com.WebDatabaseApplication.entity.User;
import com.WebDatabaseApplication.repository.ProductRepo;
import com.WebDatabaseApplication.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
     private  UserService userService;



    @GetMapping("/inventory")
    public String inventory(@AuthenticationPrincipal User user, Principal principal, Model model) {
        model.addAttribute("product", productRepo.findAllByAuthor(user));
        if (userService.isAdmin(principal.getName())){
            model.addAttribute("isAdmin", true);
        }
        return "inventory";
    }

    @GetMapping("/editStatus")
    public String save(
            @RequestParam("id") Product product,
            Model model) {
        product.setStatus("for sale");
        productRepo.save(product);
        return "redirect:/inventory";
    }

    @RequestMapping("/buy")
    public String buy(
            @RequestParam("id") Long id,
            @RequestParam String name,
            @RequestParam String modelProduct,
            @RequestParam String type,
            @AuthenticationPrincipal User user,
            Model model) {
         Product product = new Product(id, name, modelProduct, type, user, "new in your inventory");
        productRepo.save(product);
        model.addAttribute("product", productRepo.findById(id));

        return "redirect:/main";
    }

    @GetMapping("/tempBuy")
    public String buy(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("product", productRepo.findById(id));
        return "buy";
    }

}

