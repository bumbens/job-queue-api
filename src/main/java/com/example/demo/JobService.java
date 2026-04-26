package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job createJob(Job job) {
        job.setCreatedAt(LocalDateTime.now());
        job.setStatus(JobStatus.PENDING);
        return jobRepository.save(job);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException(id));
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> getJobByStatus(JobStatus status) {
        return jobRepository.findByStatus(status);
    }

    public Job updateJob(Long id, Job updatedJob) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException(id));
        job.setName(updatedJob.getName());
        job.setDescription(updatedJob.getDescription());
        job.setPriority(updatedJob.getPriority());
        job.setStatus(updatedJob.getStatus());
        job.setDueDate(updatedJob.getDueDate());
        return jobRepository.save(job);
    }
}
