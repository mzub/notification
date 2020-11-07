package ru.geekbrains.ui.service.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.ui.service.bean.Token;
import ru.geekbrains.ui.service.dto.JwtResponseToken;
import ru.geekbrains.ui.service.validation.AuthUser;
import ru.geekbrains.ui.service.validation.ProfileUser;
import ru.geekbrains.ui.service.validation.RegistrationUser;

import java.util.Objects;


@Controller
@RequestMapping("auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinderAuth) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinderAuth.registerCustomEditor(String.class, stringTrimmerEditor); // set message error
    }

    @Value("${db.url}")
    private String urlDb;

    private final Token token;
    private final AuthenticationManager authenticationManager;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registrationUser", new RegistrationUser());
        return "auth/registration-form";
    }

    @PostMapping("/register/process")
    public String processRegistrationForm(
            @ModelAttribute("registrationUser") @Validated RegistrationUser registrationUser,
            BindingResult bindingResult,
            Model model) throws JsonProcessingException {


        if (bindingResult.hasErrors()) {
            return "auth/registration-form";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(registrationUser), headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlDb + "/auth/register", HttpMethod.POST, request, String.class);
        log.info("Save user " + registrationUser.getLogin());
        return "redirect:/auth";
    }


    @GetMapping
    public String authPage(Model model) {
        model.addAttribute("authUser", new AuthUser());
        model.addAttribute("info", "ведите логин пароль");
        return "auth/auth-form";
    }

    @PostMapping("/process")
    public String processAuthForm(
            @ModelAttribute("authUser") @Validated AuthUser authUser,
            BindingResult bindingResult,
            Model model

    ) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            return "auth/auth-form";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(authUser), headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlDb + "/auth/login", HttpMethod.POST, request, String.class);

        JwtResponseToken jwtResponseToken = objectMapper.readValue(Objects.requireNonNull(responseEntity.getBody()), JwtResponseToken.class);
        token.setTokenValidateAndAuthentication(jwtResponseToken.getToken());

        log.info("set token");
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        token.setToken(null);
        return "redirect:/auth";
    }

    @GetMapping("/profile")
    public String addProfile(Model model) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        if (token.getToken() != null) {
            headers.set("Authorization", token.getToken());
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlDb + "/auth/profile", HttpMethod.GET, request, String.class);

        ProfileUser profileUser = objectMapper.readValue(Objects.requireNonNull(responseEntity.getBody()), ProfileUser.class);
        if (profileUser == null){
            profileUser = new ProfileUser();
        }
        model.addAttribute("profileUser", profileUser);
        model.addAttribute("info", "set profile");
        return "auth/profile";
    }

    @PostMapping("/profile/process")
    public String processProfileForm(
            @ModelAttribute("profileUser") @Validated ProfileUser profileUser,
            BindingResult bindingResult,
            Model model) throws JsonProcessingException {

        if (bindingResult.hasErrors()) {
            return "auth/profile";
        }
        HttpHeaders headers = new HttpHeaders();
        if (token.getToken() != null) {
            headers.set("Authorization", token.getToken());
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(profileUser), headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlDb + "/auth/profile", HttpMethod.POST, request, String.class);
        return "redirect:/home";
    }


    @ExceptionHandler
    public String handleException(HttpClientErrorException exc, Model model) {
        model.addAttribute("authUser", new AuthUser());
        model.addAttribute("info", "неверный логин или пароль");
        return "auth/auth-form";
    }

}
