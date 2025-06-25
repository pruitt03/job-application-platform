package com.example.mscompany.company;

import com.example.mscompany.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company,Long id);
    void createCompany(Company company);
    Company findCompanyById(Long id);
    boolean deleteCompanyById(Long id);
    //rabbitMQ
    public void updateCompanyRating(ReviewMessage reviewMessage);
}
