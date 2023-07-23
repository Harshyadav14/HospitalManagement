package com.HospitalManagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalManagement.Constant;
import com.HospitalManagement.model.Appointment;
import com.HospitalManagement.model.Doctor;
import com.HospitalManagement.model.Patient;
import com.HospitalManagement.model.User;
import com.HospitalManagement.repo.DoctorRepo;
import com.HospitalManagement.repo.ReceptionistRepo;
import com.HospitalManagement.repo.ReceptionistRepo2;
import com.HospitalManagement.repo.AdminRepo;

@Service
public class ReceptionistService {
	
	@Autowired
	ReceptionistRepo patientrepo;
	
	@Autowired
	ReceptionistRepo2 opdrepo;
	
	@Autowired
	AdminRepo userrepo;
	
	@Autowired
	DoctorRepo doctorrepo;
	
	public void AddOPDPatient(Appointment appointment) 
	{
		opdrepo.save(appointment);
		UpdateDoctor_OPDAppointment(appointment);
	}
	
	public void AddIPDPatient(Patient patient) 
	{
		patientrepo.save(patient);
		UpdateDoctor(patient);
	}
	
	public void UpdateDoctor_OPDAppointment(Appointment appointment)
	{
		ArrayList<String> temp=new ArrayList<String>();
		String doctorName=appointment.getDoctor_name();
		if(!(doctorrepo.findDoctorByName(doctorName)==null))
		{
			Doctor doctor=new Doctor();
			doctor=doctorrepo.findDoctorByName(doctorName);
			temp.add(String.valueOf(appointment.getOpd_id()));
			doctor.setOpd_appointments_list(temp);
			doctorrepo.saveAndFlush(doctor);
		}
	}
	
	public void UpdateDoctor(Patient patient)
	{
		ArrayList<String> temp=new ArrayList<String>();
		String doctorName=patient.getDoctor_name();
		if(!(doctorrepo.findDoctorByName(doctorName)==null))
		{
			Doctor doctor=new Doctor();
			doctor=doctorrepo.findDoctorByName(doctorName);
			temp.add(String.valueOf(patient.getIpd_id()));
			doctor.setIpd_patient_list(temp);
			doctorrepo.saveAndFlush(doctor);
		}
	}
	
	public List<Patient> showAllPatients()
	{
		return patientrepo.findAll();
	}
	
	
	
	public Optional<Patient> findPatientByPatientId(int patient_id) {
		return patientrepo.findById(patient_id);
	}
	
	public List<Patient> findAllpatientsByAdmitDate(String admit_date) {
		List<Patient> patientList=patientrepo.findAllpatientsByAdmitDate(admit_date);
		return patientList;
	}
	
	public List<Patient> findAllpatientsByDischargeDate(String discharge_date) {
		List<Patient> patientList=patientrepo.findAllpatientsByDischargeDate(discharge_date);
		return patientList;
	}
	
	public List<Patient> findAllpatientsByStatus(String status) {
		List<Patient> patientList=patientrepo.findAllpatientsByStatus(status);
		return patientList;
	}
	
	public List<Patient> findAllpatientsByBloodGroup(String blood_group) {
		List<Patient> patientList=patientrepo.findAllpatientsByBloodGroup(blood_group);
		return patientList;
	}
	
	public String DischargeRequest(int patient_id,int doctor_id)
	{
		String status="no";
		Patient patient=(Patient)patientrepo.findById(patient_id).get();
		User user=(User)userrepo.findById(doctor_id).get();
		Doctor doctor=(Doctor)doctorrepo.findById(doctor_id).get();
		
		if(user.getDesignation().toLowerCase().equals("doctor") && patient.getDischarge_request_status()==0)
		{
			ArrayList<String> temp=new ArrayList<String>();
			status="yes";
			patient.setDischarge_request_status(Constant.discharge_request_status_True);
			patient.setDischarge_requested_from_doctor_id(doctor_id);
			patientrepo.saveAndFlush(patient);
			
			temp.add(String.valueOf(patient.getIpd_id()));
			doctor.setDischarge_request_list(temp);
			doctorrepo.saveAndFlush(doctor);
		}
		return status;
	}

	
	
	public String DischargePatient(int patient_id,String date) throws InterruptedException
	{
		String status="no";
		Patient patient=(Patient)patientrepo.findById(patient_id).get();
		if(patient.getIpd_status()==1 && patient.getDischarge_approved_status()==1)
		{
			int balance=patient.getTotal_expenses()-patient.getPaid_bill();
			System.out.println("_______________________________________________________________");
			System.out.println("Please Wait.. Clearing remaining hospital dues of Rs."+balance);
			System.out.println("_______________________________________________________________");
			Thread.sleep(10000);
			patient.setPaid_bill(patient.getTotal_expenses());
			patient.setIpd_status(Constant.ipd_status_discharged);
			patient.setDischarge_date(date);
			patientrepo.saveAndFlush(patient);
			status="yes";
		}
		return status;
	}
	
	public String Payment(int patient_id,int amount) throws InterruptedException
	{
		String status="no";
		Patient patient=new Patient();
		patient=(Patient)patientrepo.findById(patient_id).get();
		if(!(patient==null))
		{
			int paidbill=patient.getPaid_bill()+amount;
			int balance=patient.getTotal_expenses()-paidbill;
			System.out.println("_______________________________________________________________");
			System.out.println("Please Wait.. Clearing hospital dues of Rs."+amount);
			Thread.sleep(5000);
			System.out.println("Remaining Unpaid bill of Rs."+balance);
			System.out.println("_______________________________________________________________");
			patient.setPaid_bill(paidbill);
			patientrepo.saveAndFlush(patient);
			status="yes";
		}
		
		 return status;
	}
	
	
	

	public void updatePatientDetails(Patient patient) {
		patientrepo.saveAndFlush(patient);
	}
	
	public void deletePatient(int patient_id) {
		patientrepo.deleteById(patient_id);
	}
	
	public List<Appointment> showAllAppointments()
	{
		return opdrepo.findAll();
	}
	
	public Optional<Appointment> findOPDPatientBy_OPDPatientId(int opd_patient_id) {
		return opdrepo.findById(opd_patient_id);
	}

	public void updateOPDPatient(Appointment appointment) {
		opdrepo.saveAndFlush(appointment);
	}
	
	public void deleteOPDPatient(int opd_patient_id) {
		opdrepo.deleteById(opd_patient_id);
	}

}
