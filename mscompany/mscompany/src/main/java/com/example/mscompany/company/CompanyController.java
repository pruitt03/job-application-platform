package com.example.mscompany.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long id){
        Company comp = companyService.findCompanyById(id);
        if(comp != null) return new ResponseEntity<>(comp,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company){
        boolean updated = companyService.updateCompany(company,id);
        if(updated)
            return new ResponseEntity<>("Company details updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Company doesnt exist",HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created succesfully.",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean del = companyService.deleteCompanyById(id);
        if(del) return new ResponseEntity<>("company deleted succesfully",HttpStatus.OK);
        return new ResponseEntity<>("company with id "+id+" not found",HttpStatus.NOT_FOUND);
    }
}
