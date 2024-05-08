package com.abuzar.jobms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abuzar.jobms.dto.JobDTO;
import com.abuzar.jobms.model.Job;
import com.abuzar.jobms.service.JobService;

@RequestMapping("/jobs")
@RestController
public class JobController {
	JobService jobService;
	
	
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	@GetMapping
	public ResponseEntity<List<JobDTO>> findAll()
	{
		return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addJobs(@RequestBody Job job)
	{
		jobService.addJobs(job);
		return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobDTO> findById(@PathVariable Long id)
	{
		JobDTO jobDTO = jobService.findById(id);
		if(jobDTO != null)
		{
			return new ResponseEntity<>(jobDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id)
	{
		Boolean isDeleted = jobService.deleteById(id);
		if(isDeleted) {
			return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Job Id Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob)
	{
		Boolean isUpdated = jobService.updateJob(id, updatedJob);
		if(isUpdated)
		{
			return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
		} else {
			
		}	return new ResponseEntity<>("Failed to Update Job", HttpStatus.NOT_FOUND);
		
	}
}
