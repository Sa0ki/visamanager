package com.visamanager.interfaces;

import com.visamanager.models.Application;
import org.springframework.data.domain.Page;

public interface IApplication {
    public void addApplication(Application application);
    public void deleteApplicationById(Long id);
    public void updateApplication(Application application);
    public Application getApplicationById(Long id);
    public Page<Application> getAllApplications(int page, int size);
    public Page<Application> findApplicationByClientName(String name, int page, int size);
}
