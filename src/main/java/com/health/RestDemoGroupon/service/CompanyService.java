package com.health.RestDemoGroupon.service;

import com.health.RestDemoGroupon.model.Company;

import java.util.List;

public interface CompanyService {

    Company findById(long id);
    List<Company> findAllCompany();

    void saveCompany(Company company);
    void updateCompany(Company company);
    void deleteCompanyById(long id);
}
