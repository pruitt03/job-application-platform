package com.example.msjob.job;

import com.example.msjob.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void addJob(Job job);
    JobDTO getJobById(Long id);
    boolean deleteJobById(Long id);

    boolean UpdateJobById(Long id, Job updatedJob);
}
