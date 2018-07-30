package com.kodgemisi.course.ecommerce.admin;

import com.kodgemisi.course.ecommerce.category.Category;
import com.kodgemisi.course.ecommerce.category.CategoryService;
import com.kodgemisi.course.ecommerce.category.CategoryValidator;
import com.kodgemisi.course.ecommerce.user.User;
import com.kodgemisi.course.ecommerce.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CategoryService categoryService;

    private final CategoryValidator categoryValidator;

    @InitBinder
    void addCategoryValidator(WebDataBinder binder) {
        binder.addValidators(this.categoryValidator);
    }

    @GetMapping
    public String adminDashboard(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin/index";
    }

    @PostMapping("/categories")
    public String addNewCategory(@Valid Category category,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("successMessage", "Successfully created");
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", bindingResult.getFieldError("name").getDefaultMessage());
        }

        return "redirect:/admin";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categoryService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Successfully created");
        return "redirect:/admin";
    }

}
