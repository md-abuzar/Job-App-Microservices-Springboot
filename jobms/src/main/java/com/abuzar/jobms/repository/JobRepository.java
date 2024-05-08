package com.abuzar.jobms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abuzar.jobms.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
