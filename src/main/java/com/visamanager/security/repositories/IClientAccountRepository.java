package com.visamanager.security.repositories;

import com.visamanager.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientAccountRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
}
