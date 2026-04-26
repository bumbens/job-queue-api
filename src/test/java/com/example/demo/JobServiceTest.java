package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class JobServiceTest {
    
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;


    @Test
    public void testGetAllJobs() {
        when(jobRepository.findAll()).thenReturn(List.of(
            new Job(1L, "Test Job 1", "Description 1", Priority.LOW, JobStatus.PENDING, LocalDateTime.now(), LocalDate.now().plusDays(1)),
            new Job(2L, "Test Job 2", "Description 2", Priority.MEDIUM, JobStatus.IN_PROGRESS, LocalDateTime.now(), LocalDate.now().plusDays(2))
        ));
        List<Job> jobs = jobService.getAllJobs();
        assertEquals(2, jobs.size());
        assertEquals("Test Job 1", jobs.get(0).getName());
        assertEquals(JobStatus.IN_PROGRESS, jobs.get(1).getStatus());
    }

    @Test
    public void testGetJobByID() {
        Job jobMock = new Job(1L, "Test Job 1", "Description 1", Priority.LOW, JobStatus.PENDING, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(jobRepository.findById(1L)).thenReturn(Optional.of(jobMock));
        Job job = jobService.getJobById(1L);
        assertEquals("Test Job 1", job.getName());
    }

    @Test
    public void testGetJobByID_NotFound() {
        when(jobRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(JobNotFoundException.class, () -> jobService.getJobById(999L));
    }

}
