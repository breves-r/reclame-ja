package com.gft.ReclameJa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gft.ReclameJa.entities.Company;
import com.gft.ReclameJa.repositories.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyService {

	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}
	
	public List<Company> getAll() {
		return companyRepository.findAll();
	}
	
	public Company findCompany(Long id) {
		return companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company not found"));
	}
	
	public List<Company> findCompanyByName(String name) {
		return companyRepository.findByNameContainingIgnoreCase(name);
	}
	
	public Company update(Company company, Long id) {
		Company companyBd = this.findCompany(id);
		company.setId(companyBd.getId());
		
		return this.saveCompany(company);
	}
	
	public void delete(Long id) {
		Company company = this.findCompany(id);
		
		companyRepository.delete(company);
	}
}
