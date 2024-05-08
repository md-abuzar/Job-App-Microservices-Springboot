package com.abuzar.jobms.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abuzar.jobms.client.CompanyClient;
import com.abuzar.jobms.client.ReviewClient;
import com.abuzar.jobms.dto.JobDTO;
import com.abuzar.jobms.external.Company;
import com.abuzar.jobms.external.Review;
import com.abuzar.jobms.mapper.JobMapper;
import com.abuzar.jobms.model.Job;
import com.abuzar.jobms.repository.JobRepository;


@Service
public class JobService {
	
	JobRepository jobRepository;
	CompanyClient companyClient;
	ReviewClient reviewClient;
	
	public JobService(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
		this.jobRepository = jobRepository;
		this.companyClient = companyClient;
		this.reviewClient = reviewClient;
	}
	
	
	public List<JobDTO> findAll()
	{
		List<Job> jobs = jobRepository.findAll();
		
		return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	
	private JobDTO convertToDTO(Job job)
	{
			Company company = companyClient.getCompany(job.getCompanyId());
			List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
			
			JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
		
			jobDTO.setCompany(company);
			
			return jobDTO;
		}	
	
	
	public JobDTO findById(Long id)
	{
		Job job = jobRepository.findById(id).orElse(null);
		Company company = companyClient.getCompany(job.getCompanyId());
		List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
		JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
		jobDTO.setCompany(company);
		return jobDTO;
	}
	
	
	public void addJobs(Job job)
	{
		jobRepository.save(job);
		
	}
	
	
	
	public Boolean deleteById(Long id)
	{
		try {
		jobRepository.deleteById(id);
		return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Boolean updateJob(Long id , Job updateJob)
	{
		Optional<Job> optionalJob = jobRepository.findById(id);
		
		if(optionalJob.isPresent())
		{
			Job job = optionalJob.get();
			job.setId(updateJob.getId());
			job.setTitle(updateJob.getTitle());
			job.setDescription(updateJob.getDescription());
			job.setMinSalary(updateJob.getMinSalary());
			job.setMaxSalary(updateJob.getMaxSalary());
			job.setLocation(updateJob.getLocation());
			jobRepository.save(job);
			return true;
		}
		return false;
	}

}
