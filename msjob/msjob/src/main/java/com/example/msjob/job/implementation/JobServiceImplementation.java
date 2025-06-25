package com.example.msjob.job.implementation;


import com.example.msjob.job.Job;
import com.example.msjob.job.JobRepository;
import com.example.msjob.job.JobService;
import com.example.msjob.job.clients.CompanyClient;
import com.example.msjob.job.clients.ReviewClient;
import com.example.msjob.job.dto.JobDTO;
import com.example.msjob.job.external.Company;
import com.example.msjob.job.external.Review;
import com.example.msjob.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImplementation implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;


    @Autowired //spring provides instance of it at runtime
    RestTemplate restTemplate;

    public JobServiceImplementation(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {

        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

   // private Long nextId = 1L;

    int attempt=0;
    @Override
    @CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    @Retry(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    /*public List<Job> findAll() {
        return jobRepository.findAll();
    }*/
    public List<JobDTO> findAll(){
        System.out.println("Attempt: "+ ++attempt);
        List<Job> jobs = jobRepository.findAll();//get all jobs
        List<JobDTO> jobDTOList = new ArrayList<>();

        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
        /*for(Job job : jobs) {
            JobWithCompanyDTO dto = convertToDTO(job); // convert each job to DTO
            jobWithCompanyDTOList.add(dto);// add to final list
        }
        return jobWithCompanyDTOList;*/
    }
    public List<String> companyBreakerFallback(Exception e){
        List<String> l = new ArrayList<>();
        l.add("Dummy");
        return l;
    }
    private JobDTO convertToDTO(Job job){
        //JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();//create a object
        //jobWithCompanyDTO.setJob(job);//add job to it
        //RestTemplate restTemplate = new RestTemplate();
        //Company company = restTemplate.getForObject("http://ms-company/companies/"+job.getCompanyId(),Company.class);
        //job will be posted by one company only=> single object
        //but can have multiple reviews




        /* ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://ms-review:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
        });

        List<Review> reviews = reviewResponse.getBody();*/

        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job,company,reviews);
        //jobDTO.setCompany(company);
        return jobDTO;
    }

    @Override
    public void addJob(Job job) {
        //job.setId(nextId++);
        //jobs.add(job);
        jobRepository.save(job);
    }

    @Override

       // return (Job) jobRepository.findById(id).orElse(null);

    public JobDTO getJobById(Long id){
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDTO(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
       if(jobRepository.existsById(id)){
           jobRepository.deleteById(id);
           return true;
       }else{
           return false;
       }
    }

    @Override
    public boolean UpdateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());

            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
