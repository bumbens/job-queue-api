package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    private LocalDateTime createdAt;
    private LocalDate dueDate;


    public Job(Long id, String name, String description, Priority priority, JobStatus status, LocalDateTime createdAt,
            LocalDate dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
    }

    public Job() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public JobStatus getStatus() {
        return status;
    }
    public void setStatus(JobStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    
}
