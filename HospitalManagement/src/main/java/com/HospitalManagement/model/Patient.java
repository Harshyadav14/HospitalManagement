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
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ipd_id;
	
	@NotBlank(message = "Invalid Name: Blank")
    @NotNull(message = "Invalid Name:  NULL")
	@Size(min = 3, max = 30, message = "Invalid Patient Name: Name Must be of 3 - 30 characters")
	@Column(name="patient_name")
	private String patient_name;
	
	@Min(value = 1, message = "Invalid Age: Equals to zero or Less than zero")
    @Max(value = 120, message = "Invalid Age: Exceeds 120 years")
	@Column(name="age")
	private int age;
	
	@NotBlank(message = "Invalid Phone number: Blank")
	@NotNull(message = "Invalid Phone number:  NULL")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number: Phone number must be of 10 digits")
	@Column(name="phone_number")
	public String phone_number;
	
	
	@NotBlank(message = "Invalid gender: Blank")
	@NotNull(message = "Invalid gender:  NULL")
	@Pattern(regexp="(?:male|Male|female|Female|FEMALE|MALE)$",message = "Invalid gender: Gender Must be either Male or Female")
	@Column(name="gender")
	private String gender;
	
	@NotBlank(message = "Invalid blood group: Blank")
	@NotNull(message = "Invalid blood group:  NULL")
	@Pattern(regexp="^(A|B|AB|O)[-+]$",message = "Invalid blood group: blood group should be one of the following A+|A-|B+|B-|AB+|AB-|O+|O-")
	@Column(name="blood_group")
	private String blood_group;
	
	@Min(value = 1, message = "Invalid Room No. : Equals to zero or Less than zero")
    @Max(value =400 , message = "Invalid Room No. : Exceeds 400 years")
	@Column(name="room_no")
	private int room_no;
	

	@NotBlank(message = "Invalid doctor name: Blank")
    @NotNull(message = "Invalid doctor name: NULL")
	@Size(min = 3, max = 30, message = "Invalid Doctor Name: Name Must be of 3 - 30 characters")
	@Column(name="doctor_name")
	private String doctor_name;
    
	@Column(name="medicine_prescription")
	private ArrayList<String> medicine_prescription=null;
	
	
	@Min(value = 1, message = "Invalid status : Equals to zero or Less than zero : admitted =1, discharged=2")
    @Max(value = 2, message = "Invalid status : Exceeds 2 : admitted =1, discharged=2")
	@Column(name="ipd_status")
	private int ipd_status=Constant.ipd_status_admitted;   //status(admitted =1, discharged=2)
	
	
	@Min(value = 0, message = "Invalid status : Less than zero : discharge Not Requested By Patient =0, discharged Requested By Patient=1")
    @Max(value = 1, message = "Invalid status : Exceeds 2 : discharge Not Requested By Patient =0, discharged Requested By Patient=1")
	@Column(name="discharge_request_status")
	private int discharge_request_status=Constant.Default_discharge_request_status;   //status(discharge Not Requested By Patient =0, discharged Requested By Patient=1)
	
	@Column(name="discharge_requested_from_doctor_id")
	private int discharge_requested_from_doctor_id=Constant.Default_discharge_requested_from_doctor_id;
	
	
	@Min(value = 0, message = "Invalid status : Less than zero : discharge Not Approved =0, discharged Approved=1")
    @Max(value = 1, message = "Invalid status : Exceeds 2 : discharge Not Approved =0, discharged Approved=1")
	@Column(name="discharge_approved_status")
	private int discharge_approved_status=Constant.discharge_approved_status_Not_Approved;   //status(discharge Not Approved =0, discharged Approved=1)
	

	@Column(name="discharge_approvedby")
	private String discharge_approvedby=Constant.discharge_approvedby;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide a admit date.")
	@Column(name="admit_date")
    private String admit_date;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="discharge_date")
    private String discharge_date=Constant.Default_discharge_date;
	
	
	@Min(value = 1, message = "Invalid expenses : Equals to zero or Less than zero")
    @Max(value = 10000000, message = "Invalid expenses : Exceeds 10000000 or 1cr.")
	@Column(name="total_expenses")
	private int total_expenses=Constant.AdmitCharges; 
	
	@Min(value = 0, message = "Invalid expenses : Less than zero")
    @Max(value = 10000000, message = "Invalid expenses : Exceeds 10000000 or 1cr.")
	@Column(name="paid_bill")
	private int paid_bill=Constant.Default_billpaid;

	
}
