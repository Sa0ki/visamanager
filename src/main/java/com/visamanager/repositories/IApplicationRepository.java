package com.visamanager.repositories;

import com.visamanager.models.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApplicationRepository extends JpaRepository<Application, Long> {
    public Page<Application> findByClient_EmailIgnoreCaseContains(PageRequest pr, String input);
}
