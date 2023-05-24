package com.visamanager.security.interfaces;

import com.visamanager.models.Admin;
import com.visamanager.models.Client;
import com.visamanager.security.models.Role;

import java.time.LocalDate;

public interface IAdminAccount {
    Admin addNewAdmin(String firstName, String lastName, LocalDate dob, String phone, String secretCode, String password, String confirmPassword) throws Exception;
    Role addNewRole(String roleName);
    Boolean addRoleToAdmin(String secretCode, String roleName);
    Boolean removeRoleFromAdmin(String secretCode, String roleName);
    Admin loadAdminBySecretCode(String secretCode);
}
