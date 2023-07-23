package com.HospitalManagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalManagement.Constant;
import com.HospitalManagement.model.Appointment;
import com.HospitalManagement.model.Doctor;
import com.HospitalManagement.model.Patient;
import com.HospitalManagement.model.User;
import com.HospitalManagement.repo.AdminRepo;
import com.HospitalManagement.repo.DoctorRepo;
import com.HospitalManagement.repo.PharmacistRepo;
import com.HospitalManagement.repo.ReceptionistRepo;
import com.HospitalManagement.repo.ReceptionistRepo2;

@Service
public class DoctorService {
	
	
	@Autowired
	DoctorRepo doctorrepo;
	
	@Autowired
	ReceptionistRepo patientrepo;
	
	@Autowired
	AdminRepo userrepo;
	
	@Autowired
	ReceptionistRepo2 opdrepo;
	
	@Autowired
	PharmacistRepo pharmrepo;
	
	public List<Doctor> showAllDoctors()
	{
		return doctorrepo.findAll();
	}
	
	public String ApproveDischarge(int patient_id,int doctor_id)
	{
		String status="no";
		Patient patient=(Patient)patientrepo.findById(patient_id).get();
		User user=(User)userrepo.findById(doctor_id).get();
		Doctor doctor=(Doctor)doctorrepo.findById(doctor_id).get();
		if(user.getDesignation().toLowerCase().equals("doctor") && patient.getDischarge_requested_from_doctor_id()==doctor_id)
		{
			ArrayList<String> temp=new ArrayList<String>();
			status="yes";
			patient.setDischarge_approvedby(user.getUser_name());
			patient.setDischarge_approved_status(Constant.discharge_approved_status_Approved);
			patientrepo.saveAndFlush(patient);
			
			temp.add(String.valueOf(patient.getIpd_id()));
			doctor.setDischarge_approve_list(temp);
			doctorrepo.saveAndFlush(doctor);
		}
		return status;
	}
	
	public String TreatOpdPatients(int patient_id,String prescription) throws InterruptedException
	{
		ArrayList<String> medicineList=new ArrayList<String>();
		int totalbill=0;
		String status="no";
		if(!(prescription==null) && !((Appointment)opdrepo.findById(patient_id).get()==null))
		{
			String[] arrOfStr = prescription.split("-");
	        for (String a : arrOfStr)
	        {
	        	if((pharmrepo.findMedicineStockFromId(a)>Constant.MedicineMinimumStockCount))
	        	{
	        		totalbill+=(pharmrepo.findMedicinePriceFromId(a)*(Constant.OPD_Medicine_DayLimit*Constant.OPD_Medicine_dosePerDay)); //Medicine for 2 Days
	        	}
	        	medicineList.add(a);
	        }  
	        Appointment patient=new Appointment();
			patient=(Appointment)opdrepo.findById(patient_id).get();
			System.out.println("_______________________________________________________________");
			System.out.println("Loading Prescription.........");
			Thread.sleep(5000);
			patient.setMedicine_prescription(medicineList);
			System.out.println("Medicine Id = "+prescription);
			System.out.println("Medicine of Rs. "+totalbill+" provided to patient");
			System.out.println("_______________________________________________________________");
			patient.setOpd_status(Constant.opd_status_Treatment_Done);
			patient.setTreatment_bill(patient.getTreatment_bill()+totalbill);
			opdrepo.saveAndFlush(patient);
			status="yes";
	        
		}
		 return status;
	}

	
	public String MedicinePrescription(int patient_id,String prescription) throws InterruptedException
	{
		int totalbill=0;
		ArrayList<String> medicineList=new ArrayList<String>();
		String status="no";
		if(!(prescription==null) && !((Patient)patientrepo.findById(patient_id).get()==null))
		{
			String[] arrOfStr = prescription.split("-");
	        for (String a : arrOfStr)
	        {
	        	if((pharmrepo.findMedicineStockFromId(a)>Constant.MedicineMinimumStockCount))
	        	{
	        		totalbill+=(pharmrepo.findMedicinePriceFromId(a)*(Constant.OPD_Medicine_DayLimit*Constant.OPD_Medicine_dosePerDay)); //Medicine for 2 Days
	        	}
	        	medicineList.add(a);
	        }  
	        Patient patient=new Patient();
			patient=(Patient)patientrepo.findById(patient_id).get();
			System.out.println("_______________________________________________________________");
			System.out.println("Loading Prescription.........");
			Thread.sleep(5000);
			patient.setMedicine_prescription(medicineList);
			patient.setTotal_expenses(patient.getTotal_expenses()+totalbill);
			System.out.println("Medicine Id = "+prescription);
			System.out.println("Medicine of Rs. "+totalbill+" provided to patient");
			System.out.println("_______________________________________________________________");
			patientrepo.saveAndFlush(patient);
			status="yes";
		}
		 return status;
	}

}
