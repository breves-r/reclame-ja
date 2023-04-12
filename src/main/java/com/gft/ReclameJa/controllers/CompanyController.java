package com.gft.ReclameJa.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.ReclameJa.entities.Company;
import com.gft.ReclameJa.services.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanys() {
		return ResponseEntity.ok(companyService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
		return ResponseEntity.status(201).body(companyService.saveCompany(company));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Company> getCompany(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(companyService.findCompany(id));
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("company/{name}")
	public ResponseEntity<List<Company>> getCompanyByName(@PathVariable String name) {
		return ResponseEntity.ok(companyService.findCompanyByName(name));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Company> updateCompany(@RequestBody Company company, @PathVariable Long id) {
		try {
			return ResponseEntity.ok(companyService.update(company, id));
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Company> deleteCompany(@PathVariable Long id) {
		try {
			companyService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
}
