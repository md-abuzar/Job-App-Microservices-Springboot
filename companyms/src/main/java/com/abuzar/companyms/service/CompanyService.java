package com.abuzar.companyms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abuzar.companyms.model.Company;
import com.abuzar.companyms.repository.CompanyRepository;


@Service
public class CompanyService {
	
	CompanyRepository companyRepository;
	
	
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	public  List<Company> getAllCompanies(){
		return companyRepository.findAll();
	}
	
	public Company findCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}

	public void createCompany(Company company) {
		companyRepository.save(company);
		
	}

	public Boolean deleteCompany(Long id) {
		if(companyRepository.existsById(id))
		{
			companyRepository.deleteById(id);
			return true;
		} else {
		return false;
		}
	}
		
	public Boolean updateCompany(Long id, Company updatedCompany) {
		Optional<Company> optionalCompany = companyRepository.findById(id);
		if (optionalCompany.isPresent())
		{
			Company company = optionalCompany.get();
			company.setName(updatedCompany.getName());
			company.setDescription(updatedCompany.getDescription());
			companyRepository.save(company);
			return true;
		} else {
			return false;
		}
		
	}
		
}
