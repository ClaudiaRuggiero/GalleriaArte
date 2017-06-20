package it.uniroma3.galleria.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.galleria.model.Admin;
import it.uniroma3.galleria.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Transactional
	public void addAdmin(Admin admin) {
		this.adminRepository.save(admin);
	}
	
	public List<Admin> findAll() {
		return this.adminRepository.findAll();
	}
	
	public Admin findByUsername(String username) {
		return this.adminRepository.findByUsername(username);
	}
	
	public Admin findByEmail(String email) {
		return this.adminRepository.findByEmail(email);
	}
	
	public void deleteAdmin(Admin admin) {
		this.adminRepository.delete(admin);
	}
}
