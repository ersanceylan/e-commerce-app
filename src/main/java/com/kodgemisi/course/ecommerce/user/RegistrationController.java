package com.kodgemisi.course.ecommerce.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    private final RegistrationDtoValidator registrationDtoValidator;

    @InitBinder("registrationDto")
    void addRegistrationDtoValidator(WebDataBinder binder) {
        binder.addValidators(this.registrationDtoValidator);
    }

    @GetMapping
    public String registrationPage(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("registrationDto", registrationDto);
        return "user/register";
    }

    @PostMapping
    public String registerUser(@Valid RegistrationDto registrationDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        registrationService.createUser(registrationDto);

        return "redirect:/login";
    }

}
