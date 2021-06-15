package com.apextech.service;

import com.apextech.model.Company;
import com.apextech.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public void update(Company company) {
        if (company.getId() != 0) {
            Company companyUpdate = companyRepository.findById(company.getId()).orElse(null);
            assert companyUpdate != null;
            companyUpdate.setName(company.getName());
            companyRepository.save(companyUpdate);
        }
    }
}
