package com.visamanager.services;

import com.visamanager.interfaces.IClient;
import com.visamanager.models.Client;
import com.visamanager.repositories.IClientRepository;
import com.visamanager.security.interfaces.IClientAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClient{
    private IClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;
    private IClientAccount clientAccount;

    @Autowired
    public ClientService(IClientRepository clientRepository, PasswordEncoder passwordEncoder, IClientAccount clientAccount){
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientAccount = clientAccount;
    }
    public Client addClient(Client client){
        clientAccount.addRoleToClient(client.getEmail(), "USER");
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    public void deleteClientById(Long id){
        clientRepository.deleteById(id);
    }

    public void updateClient(Client client){
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).get();
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Page<Client> getAllClients(int page, int size){
        return clientRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Client> findClientByEmail(String input, int page, int size){
        return clientRepository.findByEmailIgnoreCaseContains(PageRequest.of(page, size), input);
    }

    public Client findClientByEmail(String email){
        return clientRepository.findByEmail(email);
    }
}
