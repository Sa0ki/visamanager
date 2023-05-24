package com.visamanager.security.models;

import com.visamanager.models.Admin;
import com.visamanager.models.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Roles")
@Data @AllArgsConstructor @NoArgsConstructor
public class Role{
    @Id
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="admins_roles",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Admin> admins;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="clients_roles",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> clients;

    public Role(String name){
        this.name = name;
    }
}
