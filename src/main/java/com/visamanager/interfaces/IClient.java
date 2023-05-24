package com.visamanager.interfaces;

import com.visamanager.models.Client;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IClient {
    public Client addClient(Client client);
    public void deleteClientById(Long id);
    public void updateClient(Client client);
    public Client getClientById(Long id);
    public Page<Client> getAllClients(int page, int size);
    public List<Client> getAllClients();
    public Page<Client> findClientByEmail(String name, int page, int size);
}
