package com.HospitalManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HospitalManagement.model.Medicine;
import com.HospitalManagement.repo.PharmacistRepo;


@Service
public class PharmacistService {
	
	@Autowired
	PharmacistRepo medrepo;
	
	public void AddMedicine(Medicine med) 
	{
		medrepo.save(med);
	}
	
	public List<Medicine> showAllMedicines()
	{
		return medrepo.findAll();
	}
	
	public Optional<Medicine> findMedicineById(int medicine_id) {
		return medrepo.findById(medicine_id);
	}
	
	public void updateMedicine(Medicine med) {
		medrepo.saveAndFlush(med);
	}
	
	public void deleteMedicine(int medicine_id) {
		medrepo.deleteById(medicine_id);
	}
}
