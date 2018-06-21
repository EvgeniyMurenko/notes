package com.notes.controller;

import com.notes.domain.User;
import com.notes.domain.dto.CaptchaResponseDto;
import com.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;

    @Value("${recaptcha.secret}")
    private String secretCaptcha;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2") String confirmPassword,
                          @RequestParam("g-recaptcha-response") String captchaResponse,
                          @Valid User user, BindingResult bindingResult, Model model) {

        String url = String.format(CAPTCHA_URL, secretCaptcha, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        boolean isConfirmPassword = StringUtils.isEmpty(confirmPassword);
        if (isConfirmPassword) {
            model.addAttribute("password2Error", "Confirm password cannot be empty!");
        }

        if (user.getPassword() != null && !user.getPassword().equals(confirmPassword)) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (isConfirmPassword || bindingResult.hasErrors() || !response.isSuccess()) {
            Map<String, String> errors = UtilController.getErrors(bindingResult);
            model.mergeAttributes(errors);

        }

        if (model.asMap().size() > 2) {
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String userActivate(Model model, @PathVariable String code) {

        boolean isActivated = userService.activeteUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
            model.addAttribute("messageStyle", "success");
        } else {
            model.addAttribute("message", "Activation code is not found!");
            model.addAttribute("messageStyle", "danger");
        }


        return "login";
    }
}
