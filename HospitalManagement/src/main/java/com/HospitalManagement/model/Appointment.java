package com.HospitalManagement.model;


import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.HospitalManagement.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int opd_id;
	
	@Column(name="receptionist_id")
	private int receptionist_id;
	
	@NotBlank(message = "Invalid Name: Blank")
    @NotNull(message = "Invalid Name:  NULL")
	@Size(min = 3, max = 30, message = "Invalid Patient Name: Name Must be of 3 - 30 characters")
	@Column(name="patient_name")
	private String patient_name;
	
	@Min(value = 1, message = "Invalid Age: Equals to zero or Less than zero")
    @Max(value = 120, message = "Invalid Age: Exceeds 120 years")
	@Column(name="age")
	private int age;
	
	@NotBlank(message = "Invalid Phone number: Empty number")
	@NotNull(message = "Invalid Phone number: Number is NULL")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number: Phone number must be of 10 digits")
	@Column(name="phone_number")
	public String phone_number;
	
	
	@NotBlank(message = "Invalid gender: Empty gender")
	@NotNull(message = "Invalid gender: gender is NULL")
	@Pattern(regexp="(?:male|Male|female|Female|FEMALE|MALE)$",message = "Invalid gender: Gender Must be either Male or Female")
	@Column(name="gender")
	private String gender;

	
	@NotBlank(message = "Invalid blood group: Empty blood group")
	@NotNull(message = "Invalid blood group: blood group is NULL") 
	//@Pattern(regexp="(?:(A+)|(A-)|(B+)|(B-)|(AB+)|(AB-)|(O+)|(O-))$",message = "Invalid blood group: blood group should be one of the following A+|A-|B+|B-|AB+|AB-|O+|O-")
	@Pattern(regexp="^(A|B|AB|O)[-+]$",message = "Invalid blood group: blood group should be one of the following A+|A-|B+|B-|AB+|AB-|O+|O-")
	@Column(name="blood_group")
	private String blood_group;
	

	@NotBlank(message = "Invalid doctor name: Blank")
    @NotNull(message = "Invalid doctor name: NULL")
	@Size(min = 3, max = 30, message = "Invalid Doctor Name: Name Must be of 3 - 30 characters")
	@Column(name="doctor_name")
	private String doctor_name;
    
	@Column(name="medicine_prescription")
	private ArrayList<String> medicine_prescription=null;
	
	
	@Min(value = 1, message = "Invalid status : Equals to zero or Less than zero : Not Treated =1, Treatment Done=2")
    @Max(value = 2, message = "Invalid status : Exceeds 2 : Not Treated =1, Treatment Done=2")
	@Column(name="opd_status")
	private int opd_status=Constant.opd_status_Not_Treated;   //status(Not Treated =1, Treatment Done=2)
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide a admit date.")
	@Column(name="appointment_date")
    private String appointment_date;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Pattern(regexp="^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$",message = "Invalid appointment_time: appointment_time should be 24-hour time format of HH:MM ")
	@Column(name="appointment_time")
    private String appointment_time=null;   
	
	@Min(value = 0, message = "Invalid expenses : Less than zero")
    @Max(value = 10000000, message = "Invalid expenses : Exceeds 10000000 or 1cr.")
	@Column(name="treatment_bill")
	private int treatment_bill=Constant.AppointmentCharges;

	
}
