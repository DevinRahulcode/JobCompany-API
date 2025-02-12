package com.example.Jobsapp.controller;


import com.example.Jobsapp.exception.RecordNotFoundException;
import com.example.Jobsapp.model.Company;

import com.example.Jobsapp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping
    public Company addCompany(@RequestBody Company company){
        return companyRepository.save(company);
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Company> findById( @PathVariable int id){
        Company company = companyRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No Such Record Exists" +id));

        return  ResponseEntity.ok(company);
    }

    @PutMapping("{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody Company updateCompanyDetails){
        Company updateCompany = companyRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Record Not found"+id));

        updateCompany.setName(updateCompanyDetails.getName());
        updateCompany.setLocation(updateCompanyDetails.getLocation());

        companyRepository.save(updateCompany);
        return ResponseEntity.ok(updateCompany);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable int id){

        Company company = companyRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No record found"+id));

        companyRepository.delete(company);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
