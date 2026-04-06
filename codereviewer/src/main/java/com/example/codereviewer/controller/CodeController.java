package com.example.codereviewer.controller;

import com.example.codereviewer.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CodeController {

    @Autowired
    private CodeService service;

    // Dashboard Page
    @GetMapping("/")
    public String dashboard() {
        return "dashboard";
    }

    // Submit Code
    @PostMapping("/submit")
    public String submit(@RequestParam String developerName,
                         @RequestParam String language,
                         @RequestParam String title,
                         @RequestParam String code,
                         Model model) {

        if (developerName.trim().isEmpty() ||
                title.trim().isEmpty() ||
                code.trim().isEmpty()) {

            model.addAttribute("error", "All fields are required!");
            return "dashboard";
        }

        boolean added = service.addSubmission(developerName, language, title, code);

        if (!added) {
            model.addAttribute("error", "Duplicate submission not allowed!");
            return "dashboard";
        }

        return "redirect:/reviews";
    }
    // Review Page
    @GetMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("list", service.getAll());
        return "review";
    }

    // View Code
    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("code", service.getById(id));
        return "view";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteSubmission(id);
        return "redirect:/";
    }

    // Edit Page
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable int id, Model model) {
        model.addAttribute("code", service.getById(id));
        return "edit";
    }

    // Update
    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id,
                         @RequestParam String developerName,
                         @RequestParam String language,
                         @RequestParam String title,
                         @RequestParam String code) {

        service.updateSubmission(id, developerName, language, title, code);
        return "redirect:/reviews";
    }
}
