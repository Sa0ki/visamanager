package com.visamanager.security.services;

import com.visamanager.models.Client;
import com.visamanager.repositories.IClientRepository;
import com.visamanager.security.interfaces.IClientAccount;
import com.visamanager.security.models.Role;
import com.visamanager.security.repositories.IClientAccountRepository;
import com.visamanager.security.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Transactional
public class ClientAccountService implements IClientAccount {
    private IClientAccountRepository accountRepository;
    private IRoleRepository roleRepository;
    private IClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;

    //Ou bien on peut ajouter AllArgsContructor de Lambok.
    @Autowired
    public ClientAccountService(IClientAccountRepository accountRepository, IRoleRepository roleRepository,
                                IClientRepository clientRepository, PasswordEncoder passwordEncoder){
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Client addNewClient(String firstName, String lastName, LocalDate dob, String email, String password, String confirmPassword){
            if((accountRepository.findByEmail(email)) != null)
                return null;
            if(! password.equals(confirmPassword))
                return null;
        Client newClient =  clientRepository.save(new Client(firstName, lastName, dob, email, passwordEncoder.encode(password)));
        addRoleToClient(newClient.getEmail(), "USER");
        return newClient;
    }

    @Override
    public Role addNewRole(String roleName) {
        if((roleRepository.findByName(roleName)) != null)
            return null;
        return roleRepository.save(new Role(roleName));
    }

    @Override
    public Boolean addRoleToClient(String email, String roleName) {
        Client client = clientRepository.findByEmail(email);
        if(client == null) return false;

        Role role = roleRepository.findByName(roleName);
        if(role == null)
            role = addNewRole(roleName);

        if(client.getRoles() == null){
            client.setRoles(new ArrayList<Role>());
            client.getRoles().add(role);
            return true;
        }
        if(client.getRoles().contains(role)) return false;
        client.getRoles().add(role);
        //La méthode est transactionnelle donc le role du client va être modifié dans la base de données.
        //clientRepository.save(client);
        return true;
    }

    @Override
    public Boolean removeRoleFromClient(String email, String roleName) {
        Client client = clientRepository.findByEmail(email);
        if(client == null) return false;
        Role role = roleRepository.findByName(roleName);
        if(role == null) return false;
        client.getRoles().remove(role);
        return true;
    }

    @Override
    public Client loadClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
