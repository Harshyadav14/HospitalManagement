package com.HospitalManagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.HospitalManagement.model.Appointment;
import com.HospitalManagement.model.Doctor;
import com.HospitalManagement.model.Patient;
import com.HospitalManagement.model.User;
import com.HospitalManagement.repo.AdminRepo;
import com.HospitalManagement.repo.ReceptionistRepo2;
import com.HospitalManagement.service.ReceptionistService;

@RestController
public class ReceptionistController {
	
	@Autowired
	ReceptionistService patientservice;
	
	@Autowired
	AdminRepo userrepo;

	@PostMapping("/Create_Appointment")
	public Appointment AddOPDPatient(@Valid @RequestBody Appointment appointment) {
		User user=new User();
		user=(User)userrepo.findById(appointment.getReceptionist_id()).get();
		if(!(user==null) && user.getDesignation().toLowerCase().equals("receptionist"))
		{
			patientservice.AddOPDPatient(appointment);
		}
		return appointment;
	}
	
	@GetMapping("/find_Appointment/{opd_patient_id}")
	public Optional<Appointment> findOPDPatientBy_OPDPatientId(@Valid @PathVariable int opd_patient_id) {
		return patientservice.findOPDPatientBy_OPDPatientId(opd_patient_id);
	}
	
	@GetMapping("/View_All_Appointments")
	public List<Appointment> showAllAppointments() {
		return patientservice.showAllAppointments();
	}
	
	@PutMapping("/Update_Appointment")
	public String updateOPDPatient(@Valid @RequestBody Appointment appointment) {
		patientservice.updateOPDPatient(appointment);
		return "OPD Patient With OPD Id "+appointment.getOpd_id()+" has been Updated successfully";
	}
	
	@DeleteMapping("/Delete_Appointment/{opd_patient_id}")
	public String deleteOPDPatient(@Valid @PathVariable int opd_patient_id) {
		patientservice.deleteOPDPatient(opd_patient_id);
		return "OPD Patient With OPD Id "+opd_patient_id+" has been Deleted successfully";
	}
	
	@PostMapping("/Admit_Patient")
	public Patient AddIPDPatient(@Valid @RequestBody Patient patient) {
		patientservice.AddIPDPatient(patient);
		return patient;
	}

	@GetMapping("/View_Admited_Patients")
	public List<Patient> showAllPatients() {
		return patientservice.showAllPatients();
	}
	
	@GetMapping("/find_Admited_PatientsBy_ID/{patient_id}")
	public Optional<Patient> findPatientByPatientId(@Valid @PathVariable int patient_id) {
		return patientservice.findPatientByPatientId(patient_id);
	}
	
	@GetMapping("/find_Admited_PatientsBy_AdmitDate/{admit_date}")
	public List<Patient> findAllpatientsByAdmitDate(@Valid @PathVariable String admit_date) {
		return patientservice.findAllpatientsByAdmitDate(admit_date);
	}
	
	@GetMapping("/find_Discharge_PatientsBy_DischargeDate/{discharge_date}")
	public List<Patient> findAllpatientsByDischargeDate(@Valid @PathVariable String discharge_date) {
		return patientservice.findAllpatientsByDischargeDate(discharge_date);
	}
	
	@GetMapping("/findAllpatientsBy_Status/{status}")
	public List<Patient> findAllpatientsByStatus(@Valid @PathVariable String status) {
		return patientservice.findAllpatientsByStatus(status);
	}
	
	@GetMapping("/findAllpatientsBy_BloodGroup/{blood_group}")
	public List<Patient> findAllpatientsByBloodGroup(@Valid @PathVariable String blood_group) {
		return patientservice.findAllpatientsByBloodGroup(blood_group);
	}
	
	@PutMapping("/Update_Admited_Patient")
	public String update(@Valid @RequestBody Patient patient) {
		patientservice.updatePatientDetails(patient);
		return "Patient With patientId "+patient.getIpd_id()+" has been Updated successfully";
	}
	
	@DeleteMapping("/Delete_Admited_Patient/{patient_id}")
	public String delete(@Valid @PathVariable int patient_id) {
		patientservice.deletePatient(patient_id);
		return "Patient With patientId "+patient_id+" has been Deleted successfully";
	}
	
	
	@GetMapping("/Patient_DischargeRequest/{patient_id}/{user_id}")
	public String DischargeRequest(@PathVariable int patient_id,@PathVariable int user_id) {
		
		String status=patientservice.DischargeRequest(patient_id,user_id);
		if(status=="yes")
		{
			return "Discharge request of patient with patientId "+patient_id+" has been successfully raised";
		}
		else {
			return "Discharge request of patient with patientId "+patient_id+" failed";
		}
	}
	
	@GetMapping("/DischargePatient/{patient_id}/{date}")
	public String DischargePatient(@PathVariable int patient_id,@PathVariable String date) throws InterruptedException {
		
		String status=patientservice.DischargePatient(patient_id,date);
		if(status=="yes")
		{
			return "Patient with patientId "+patient_id+" has been discharge successfully";
		}
		else {
			return "Sorry Patient with patientId "+patient_id+" can not been discharged please contact admin for more details";
		}
	}
	
	
	@GetMapping("/Payment/{patient_id}/{amount}")
	public String Payment(@PathVariable int patient_id,@PathVariable int amount) throws InterruptedException {
		
		String status=patientservice.Payment(patient_id,amount);
		if(status=="yes")
		{
			return "Payment of Rs."+amount+" from patient Id "+patient_id+" has been recieved successfully";
		}
		else {
			return "Payment Failed";
		}
	}
}
