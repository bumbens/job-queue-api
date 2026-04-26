package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobProcessor {

    @Autowired
    private JobRepository jobRepository;
    
    @Scheduled(fixedDelay = 5000)
    public void processJobs() {
        List<Job> pendingJobs = jobRepository.findByStatus(JobStatus.PENDING);

        for (Job job : pendingJobs) {
            job.setStatus(JobStatus.IN_PROGRESS);
            jobRepository.save(job);
            // Simulate job processing
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
            job.setStatus(JobStatus.COMPLETED);
            jobRepository.save(job);
        }

    }
}