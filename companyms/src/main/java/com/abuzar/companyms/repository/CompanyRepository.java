package com.abuzar.companyms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abuzar.companyms.model.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
}
