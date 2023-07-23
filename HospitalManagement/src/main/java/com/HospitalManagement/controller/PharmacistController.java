package com.HospitalManagement.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.HospitalManagement.model.Medicine;
import com.HospitalManagement.service.PharmacistService;


@RestController
public class PharmacistController 
{
	
	@Autowired
	PharmacistService medservice;
	
	@PostMapping("/Add_Medicine")
	public Medicine AddMedicine(@Valid @RequestBody Medicine med) {
		medservice.AddMedicine(med);
		return med;
	}

	@GetMapping("/Show_All_Medicines")
	public List<Medicine> showAllMedicines() {
		return medservice.showAllMedicines();
	}
	
	@GetMapping("/findMedicineById/{medicine_id}")
	public Optional<Medicine> findMedicineById(@Valid @PathVariable int medicine_id) {
		return medservice.findMedicineById(medicine_id);
	}
	
	@PutMapping("/Update_Medicine")
	public String updateMedicine(@Valid @RequestBody Medicine med) {
		medservice.updateMedicine(med);
		return "Medicine With medicineId "+med.getMed_id()+" has been Updated successfully";
	}
	
	@DeleteMapping("/Delete_Medicine/{medicine_id}")
	public String deleteMedicine(@Valid @PathVariable int medicine_id) {
		medservice.deleteMedicine(medicine_id);
		return "Medicine With medicineId "+medicine_id+" has been Deleted successfully";
	}
}
