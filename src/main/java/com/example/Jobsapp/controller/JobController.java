package com.example.Jobsapp.controller;


import com.example.Jobsapp.exception.RecordNotFoundException;
import com.example.Jobsapp.model.Job;
import com.example.Jobsapp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @PostMapping
    public Job createJob(@RequestBody Job job){
        return jobRepository.save(job);
    }


    @GetMapping
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity<Job> getById(@PathVariable long id){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Employee Dosen't exist"+id));
        return ResponseEntity.ok(job);
    }


    @PutMapping("{id}")
    public ResponseEntity<Job> updateJob(@PathVariable long id, @RequestBody Job updateJobDetails){
        Job updateJob = jobRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Job Not Find"+id));

        updateJob.setName(updateJobDetails.getName());
        updateJob.setDescription(updateJobDetails.getDescription());

        jobRepository.save(updateJob);

        return ResponseEntity.ok(updateJob);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable long id){

        Job job = jobRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Record Not Available "+id));

        jobRepository.delete(job);

        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
