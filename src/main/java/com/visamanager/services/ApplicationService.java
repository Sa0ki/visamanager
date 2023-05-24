package com.visamanager.services;

import com.visamanager.interfaces.IApplication;
import com.visamanager.models.Application;
import com.visamanager.repositories.IApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements IApplication{
    IApplicationRepository visaApplicationRepository;
    @Autowired
    public ApplicationService(IApplicationRepository visaApplicationRepository){
        this.visaApplicationRepository = visaApplicationRepository;
    }
    public void addApplication(Application application){
        visaApplicationRepository.save(application);
    }

    public void deleteApplicationById(Long id){
        visaApplicationRepository.deleteById(id);
    }

    public void updateApplication(Application application){
        visaApplicationRepository.save(application);
    }

    public Application getApplicationById(Long id){
        return visaApplicationRepository.findById(id).get();
    }

    public Page<Application> getAllApplications(int page, int size){

        return visaApplicationRepository.findAll(PageRequest.of(page, size));
    }
    public Page<Application> findApplicationByClientName(String name, int page, int size){
        return visaApplicationRepository.findByClient_EmailIgnoreCaseContains(PageRequest.of(page, size), name);
    }
}