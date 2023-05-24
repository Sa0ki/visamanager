package com.visamanager.controllers;

import com.visamanager.models.Application;
import com.visamanager.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import com.visamanager.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/application/")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ClientService clientService;

    @RequestMapping("addApplicationForm")
    public String addApplicationForm(Model model){
        model.addAttribute("clientList", clientService.getAllClients());
        return "application/addApplicationForm";
    }
    @RequestMapping("addApplication")
    public String addApplication(@ModelAttribute("application") Application application, Model model){
        application.setDob(LocalDate.now());
        applicationService.addApplication(application);
        return getAllApplications(model, 0);
    }
    @RequestMapping("deleteApplication/{id}")
    public String deleteApplicationById(@PathVariable("id") Long id,
                                        @RequestParam(value = "name", defaultValue = "") String name,
                                        @RequestParam(name="page", defaultValue = "0") int page,
                                        Model model){
        applicationService.deleteApplicationById(id);
        if(name.isEmpty())
            return getAllApplications(model, page);

        return getApplicationsFound(name, page, model);
    }
    @RequestMapping("updateApplicationForm/{id}")
    public String updateApplicationForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("app", applicationService.getApplicationById(id));
        return "application/updateApplicationForm";
    }
    @RequestMapping("updateApplication")
    public String updateApplication(@ModelAttribute("application") Application application, Model model){
        applicationService.updateApplication(application);
        return getAllApplications(model, 0);
    }
    @RequestMapping("getApplication/{id}")
    public String getApplicationById(@PathVariable("id") Long id, Model model){
        model.addAttribute("app", applicationService.getApplicationById(id));
        return "application/getApplication";
    }
    @GetMapping(path = "getAllApplications")
    public String getAllApplications(Model model,
                                     @RequestParam(value = "page", defaultValue = "0") int page) {

        if(! model.containsAttribute("applicationList")){
            Page<Application> applicationPage = applicationService.getAllApplications(page, 4);
            model.addAttribute("applicationList", applicationPage.getContent());
            model.addAttribute("pages", new int[applicationPage.getTotalPages()]);
            model.addAttribute("currentPage", page);
        }
        return "application/getAllApplications";
    }
    @RequestMapping("getApplicationsFound")
    public String getApplicationsFound(@ModelAttribute("name") String name,
                                  @RequestParam(name="page", defaultValue = "0") int page,
                                  Model model){
        if(name.isEmpty() || name.equals(" "))
            return getAllApplications(model, 0);

        Page<Application> applicationPage = applicationService.findApplicationByClientName(name, page, 4);

        if(page > applicationPage.getTotalPages()){
            applicationPage = applicationService.findApplicationByClientName(name, --page, 4);
            if(applicationPage.getContent().isEmpty())
                return getAllApplications(model, 0);
        }

        model.addAttribute("applicationList", applicationPage.getContent());
        model.addAttribute("pages", new int[applicationPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        return "/application/getApplicationsFound";
    }
}
