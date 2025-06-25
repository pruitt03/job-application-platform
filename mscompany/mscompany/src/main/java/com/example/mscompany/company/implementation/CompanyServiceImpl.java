package com.example.mscompany.company.implementation;


import com.example.mscompany.company.Company;
import com.example.mscompany.company.CompanyRepository;
import com.example.mscompany.company.CompanyService;
import com.example.mscompany.company.clients.ReviewClient;
import com.example.mscompany.company.dto.ReviewMessage;
import feign.FeignException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepo;
    private ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepo, ReviewClient reviewClient) {
        this.companyRepo = companyRepo;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public boolean updateCompany(Company company,Long id) {
        Optional<Company> companyOptional = companyRepo.findById(id);
        if(companyOptional.isPresent()) {
            Company comp = companyOptional.get();
            comp.setName(company.getName());
            comp.setDescription(company.getDescription());

            companyRepo.save(comp);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepo.save(company);
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepo.existsById(id)) {
            companyRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        //get comp from db
        Company company = companyRepo.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new NotFoundException("company not found" + reviewMessage.getCompanyId()));

        //get avg rating of company from review ms
        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        //update company with it
        company.setRating(averageRating);
        companyRepo.save(company);

    }
}
