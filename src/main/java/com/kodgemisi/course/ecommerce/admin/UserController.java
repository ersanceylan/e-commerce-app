package com.kodgemisi.course.ecommerce.admin;

import com.kodgemisi.course.ecommerce.user.User;
import com.kodgemisi.course.ecommerce.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String manageUsers(Model model, Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        model.addAttribute("users", users);
        return "admin/user/index";
    }

    @GetMapping("/{id}")
    public String userDetails(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user/show";
    }

    @PatchMapping("/{id}")
    public String changeEnabledStatus(User user, @PathVariable("id") Long id) {
        userService.setEnabled(id, user.isEnabled());
        // check if this user authenticated
        return "redirect:/admin/users/{id}";
    }

}