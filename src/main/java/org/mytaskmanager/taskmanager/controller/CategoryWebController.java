package org.mytaskmanager.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.mytaskmanager.taskmanager.model.Category;
import org.mytaskmanager.taskmanager.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/categories")
@RequiredArgsConstructor
public class CategoryWebController {
    private final CategoryService categoryService;

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @PostMapping
    public String createCategory(Category category) {
        categoryService.create(category);
        return "redirect:/ui/categories";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/ui/categories";
    }
}
