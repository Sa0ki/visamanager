package com.visamanager.models;

import com.visamanager.security.models.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="admins")
@Data @AllArgsConstructor @NoArgsConstructor
public class Admin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String phone;
    @Column(unique = true)
    private String secretCode;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="admins_roles",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private List<Role> roles;

    public Admin(String firstName, String lastName, LocalDate dob, String phone, String secretCode, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
        this.secretCode = secretCode;
        this.password = password;
    }
}
