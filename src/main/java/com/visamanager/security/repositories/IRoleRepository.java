package com.visamanager.security.repositories;

import com.visamanager.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, String> {
    Role findByName(String roleName);
}
