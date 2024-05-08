package com.abuzar.jobms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abuzar.jobms.external.Company;

@FeignClient(name="COMPANYMS", url = "http://localhost:8082")
public interface CompanyClient {
	@GetMapping("/companies/{id}")
	Company getCompany(@PathVariable("id") Long id);
}
