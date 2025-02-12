package com.example.Jobsapp.repository;

import com.example.Jobsapp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
