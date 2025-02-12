package com.example.Jobsapp.repository;

import com.example.Jobsapp.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
