package com.visamanager.security.interfaces;
import com.visamanager.models.Client;
import com.visamanager.security.models.Role;

import java.time.LocalDate;

public interface IClientAccount {
    Client addNewClient(String firstName, String lastName, LocalDate dob, String email, String password, String confirmPassword) throws Exception;
    Role addNewRole(String roleName);
    Boolean addRoleToClient(String email, String roleName);
    Boolean removeRoleFromClient(String email, String roleName);
    Client loadClientByEmail(String email);
}
