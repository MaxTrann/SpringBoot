package vn.maxtrann.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping({ "/", "/home" })
    public String home() {
        return "redirect:/admin/products/ajax/list";
    }
}
