package com.visamanager.security.services;

import com.visamanager.models.Admin;
import com.visamanager.security.interfaces.IAdminAccount;
import com.visamanager.security.models.Role;
import com.visamanager.security.repositories.IAdminAccountRepository;
import com.visamanager.security.repositories.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class AdminAccountService implements IAdminAccount {

    private IAdminAccountRepository accountRepository;
    private IRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public Admin addNewAdmin(String firstName, String lastName, LocalDate dob, String phone, String secretCode, String password, String confirmPassword) throws Exception {
        if(accountRepository.findBySecretCode(secretCode) != null)
            return null;
        if(! password.equals(confirmPassword))
            return null;
        Admin newAdmin = accountRepository.save(new Admin(firstName, lastName, dob, phone, secretCode, passwordEncoder.encode(password)));
        addRoleToAdmin(newAdmin.getSecretCode(),"ADMIN");
        return newAdmin;
    }

    @Override
    public Role addNewRole(String roleName) {
        if(roleRepository.findByName(roleName) != null)
            return null;
        return roleRepository.save(new Role(roleName));
    }

    @Override
    public Boolean addRoleToAdmin(String secretCode, String roleName) {
        Admin admin = accountRepository.findBySecretCode(secretCode);
        if(admin == null) return false;

        Role role = roleRepository.findByName(roleName);
        if(role == null)
            role = addNewRole(roleName);

        if(admin.getRoles() == null){
            admin.setRoles(new ArrayList<Role>());
            admin.getRoles().add(role);
            return true;
        }
        if(admin.getRoles().contains(role)) return false;
        admin.getRoles().add(role);
        return true;
    }

    @Override
    public Boolean removeRoleFromAdmin(String secretCode, String roleName) {
        Admin admin = accountRepository.findBySecretCode(secretCode);
        if(admin == null) return false;
        Role role = roleRepository.findByName(roleName);
        if(role == null) return false;
        admin.getRoles().remove(role);
        return true;
    }

    @Override
    public Admin loadAdminBySecretCode(String secretCode) {
        return accountRepository.findBySecretCode(secretCode);
    }
}
