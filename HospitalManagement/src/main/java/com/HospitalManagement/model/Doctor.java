package com.HospitalManagement.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor")
public class Doctor {
	
	@Id
	@Column(name="user_id")
	private int user_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="Phoneno")
	public String Phoneno;
	
	@Column(name="email", unique = true)
	public String email;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="opd_appointments_list")
	private ArrayList<String> opd_appointments_list=null;
	
	@Column(name="ipd_patient_list")
	private ArrayList<String> ipd_patient_list=null;
	
	@Column(name="discharge_request_list")
	private ArrayList<String> discharge_request_list=null;
	
	@Column(name="discharge_approve_list")
	private ArrayList<String> discharge_approve_list=null;
	
}
