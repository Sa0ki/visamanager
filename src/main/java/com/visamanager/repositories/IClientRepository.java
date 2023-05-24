package com.visamanager.repositories;

import com.visamanager.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findByEmailIgnoreCaseContains(PageRequest pr, String input);
    Client findByEmail(String email);
}
