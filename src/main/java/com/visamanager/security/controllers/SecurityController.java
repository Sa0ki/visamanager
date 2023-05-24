package com.visamanager.security.controllers;

import com.visamanager.models.Admin;
import com.visamanager.models.Client;
import com.visamanager.security.services.AdminAccountService;
import com.visamanager.security.services.ClientAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class SecurityController {
    private ClientAccountService accountService;
    private AdminAccountService adminAccountService;

    @GetMapping("/notAuthorized")
    public String noAuthorized(){
        return "security/notAuthorized";
    }
    @GetMapping("/login")
    public String loginForm(){
        return "security/login";
    }
    @GetMapping("/register")
    public String registerForm(){
        return "security/register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute Client client, @RequestParam(name="confirmPassword") String confirmPassword, Model model){
        Client newClient = accountService.addNewClient(client.getFirstName(), client.getLastName(), client.getDob(), client.getEmail(), client.getPassword(), confirmPassword);
        if(newClient == null){
            model.addAttribute("errorMessage", "Email already exists, or passwords don't match...");
            return "security/register";
        }

        model.addAttribute("welcomeMessage", "Account created successfully ! Please log in.");
        return "security/login";
    }

    @GetMapping("/admin/register")
    public String adminRegisterForm(){
        return "security/registerAdmin";
    }

    @PostMapping("/admin/register")
    public String adminRegister(@ModelAttribute(name="admin") Admin admin, @RequestParam(name="confirmPassword") String confirmPassword, Model model) throws Exception {
        Admin newAdmin = adminAccountService.addNewAdmin(admin.getFirstName(), admin.getLastName(), admin.getDob(), admin.getPhone(), admin.getSecretCode(), admin.getPassword(), confirmPassword);
        if(newAdmin == null){
            model.addAttribute("errorMessage", "Secret code already exists, or passwords don't match...");
            return "security/registerAdmin";
        }
        model.addAttribute("welcomeMessage", "Account created successfully ! Please log in.");
        return "security/loginAdmin";
    }

    @GetMapping("/admin/login")
    public String loginAdminForm(){
        return "security/loginAdmin";
    }
}
