package com.visamanager.controllers;

import com.visamanager.models.Client;
import com.visamanager.security.controllers.SecurityController;
import com.visamanager.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private SecurityController securityController;
    @RequestMapping("/admin/client/addClientForm")
    public String addClientForm(){
        return "client/addClientForm";
    }
    @RequestMapping("/admin/client/addClient")
    public String addClient(@ModelAttribute("client") Client client, Model model){
        securityController.register(client, client.getPassword(), model);
        return getAllClients(0, model);
    }
    @RequestMapping("/admin/client/deleteClient/{id}")
    public String deleteClientById(@PathVariable("id") Long id,
                                   @RequestParam(value = "name", defaultValue = "") String name,
                                   @RequestParam(name="page", defaultValue = "0") int page,
                                   Model model){
        clientService.deleteClientById(id);
        if(name.isEmpty())
            return getAllClients(page, model);

        return getClientsFound(name, page, model);
    }

    @RequestMapping("/admin/client/updateClientForm/{id}")
    public String updateClientForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("client", clientService.getClientById(id));
        return "client/updateClientForm";
    }
    @RequestMapping("/admin/client/updateClient")
    public String updateClient(@ModelAttribute("client") Client client, Model model){
        clientService.updateClient(client);
        return getAllClients(0, model);
    }
    @RequestMapping("/admin/client/getClient/{id}")
    public String getClientById(@PathVariable("id") Long id, Model model){
        model.addAttribute("client", clientService.getClientById(id));
        return "client/getClient";
    }

    @RequestMapping("/")
    public String home(){
        return "index";
    }
    @RequestMapping("/admin/client/getAllClients")
    public String getAllClients(@RequestParam(name = "page", defaultValue = "0") int page,
                                Model model){
        if(! model.containsAttribute("clientList")){
            Page<Client> pageClient = clientService.getAllClients(page, 4);
            model.addAttribute("clientList", pageClient.getContent());
            model.addAttribute("pages", new int[pageClient.getTotalPages()]);
            model.addAttribute("currentPage", page);
        }
        return "client/getAllClients";
    }
    @RequestMapping("/admin/client/getClientsFound")
    public String getClientsFound(@ModelAttribute("name") String name,
                                   @RequestParam(name="page", defaultValue = "0") int page,
                                   Model model){
        if(name.isEmpty() || name.equals(" "))
            return getAllClients(0, model);

        Page<Client> clientPage = clientService.findClientByEmail(name, page, 4);

        if(page > clientPage.getTotalPages()){
            --page;
            clientPage = clientService.findClientByEmail(name, page, 4);
            if(clientPage.getContent().isEmpty())
                return getAllClients(0, model);
        }

        model.addAttribute("clientList", clientPage.getContent());
        model.addAttribute("pages", new int[clientPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        return "/client/getClientsFound";
    }
}
