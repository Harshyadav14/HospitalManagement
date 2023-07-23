package com.HospitalManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagement.model.Appointment;
import com.HospitalManagement.model.Doctor;
import com.HospitalManagement.service.DoctorService;
import com.HospitalManagement.service.ReceptionistService;

@RestController
public class DoctorController {
	
	@Autowired
	DoctorService doctorservice;
	
	
	@GetMapping("/Show_All_Doctors")
	public List<Doctor> showAllDoctors() {
		return doctorservice.showAllDoctors();
	}
	
	@GetMapping("/Approve_DischargeRequest/{patient_id}/{user_id}")
	public String ApproveDischarge(@PathVariable int patient_id,@PathVariable int user_id) {
		
		String status=doctorservice.ApproveDischarge(patient_id,user_id);
		if(status=="yes")
		{
			return "Discharge request of patient with patientId "+patient_id+" has been Approved successfully";
		}
		else {
			return "Discharge request of patient with patientId "+patient_id+" has been Rejected by doctor";
		}
	}
	
	@PostMapping("/Treat_OPD_Patients/{patient_id}/{prescription}/")
	public String TreatOpdPatients(@PathVariable int patient_id, @PathVariable String prescription) throws InterruptedException {
		
		String status=doctorservice.TreatOpdPatients(patient_id,prescription);
		if(status=="yes")
		{
			return "Prescription for patient with patientId "+patient_id+" has been Updated successfully";
		}
		else {
			return "No Prescription Found";
		}
	}
	
	@PostMapping("/MedicinePrescription/{patient_id}/{prescription}/")
	public String doctorPrescription(@PathVariable int patient_id, @PathVariable String prescription) throws InterruptedException {
		
		String status=doctorservice.MedicinePrescription(patient_id,prescription);
		if(status=="yes")
		{
			return "Prescription for patient with patientId "+patient_id+" has been Updated successfully";
		}
		else {
			return "No Prescription Found";
		}
	}

}
