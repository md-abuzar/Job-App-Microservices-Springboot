package com.abuzar.jobms.mapper;

import java.util.List;

import com.abuzar.jobms.dto.JobDTO;
import com.abuzar.jobms.external.Company;
import com.abuzar.jobms.external.Review;
import com.abuzar.jobms.model.Job;

public class JobMapper {
	public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews) {
		JobDTO jobDTO = new JobDTO();
		jobDTO.setId(job.getId());
		jobDTO.setTitle(job.getTitle());
		jobDTO.setDescription(job.getDescription());
		jobDTO.setMinSalary(job.getMinSalary());
		jobDTO.setMaxSalary(job.getMaxSalary());
		jobDTO.setLocation(job.getLocation());
		jobDTO.setCompany(company);
		jobDTO.setReview(reviews);
		
		return jobDTO;
	}
	

}
