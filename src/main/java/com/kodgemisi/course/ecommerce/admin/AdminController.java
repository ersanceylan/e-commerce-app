package com.kodgemisi.course.ecommerce.admin;

import com.kodgemisi.course.ecommerce.user.User;
import com.kodgemisi.course.ecommerce.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String adminDashboard() {
        return "admin/index";
    }

}
