package com.example.msjob.job;


import com.example.msjob.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {

        this.jobService = jobService;
    }

    @GetMapping
   public ResponseEntity<List<JobDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobDTO = jobService.getJobById(id);
        if(jobDTO != null) return new ResponseEntity<>(jobDTO,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job){
        jobService.addJob(job);
        return new ResponseEntity<>("Job "+job.getId()+" is successfully added",HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean exists = jobService.deleteJobById(id);
        if(exists) return new ResponseEntity<>("Job with id "+id+" is successfully deleted",HttpStatus.OK);
        return new ResponseEntity<>("Job with id " + id +" doesnt exist",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String > updateJobById(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.UpdateJobById(id,updatedJob);
        if(updated) return new ResponseEntity<>("Job Updated succesfully",HttpStatus.OK);
        return new ResponseEntity<>("Job doesnt exist",HttpStatus.NOT_FOUND);

    }

}
