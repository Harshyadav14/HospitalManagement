package com.HospitalManagement.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	
	@NotBlank(message = "Invalid Name: Blank")
    @NotNull(message = "Invalid Name:  NULL")
	@Size(min = 3, max = 30, message = "Invalid Name: Name Must be of 3 - 30 characters")
	@Column(name="user_name")
	private String user_name;
	
	@NotBlank(message = "Invalid Phone number: Blank")
	@NotNull(message = "Invalid Phone number:  NULL")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number: Phone number must be of 10 digits")
	@Column(name="phone_number")
	public String phone_number;
	
	@Email(message = "Invalid email")
	@Pattern(regexp = "^(.+)@exm(.+)com$",message = "Enter valid Email Id with domain @exm.com")
	@Column(name="email_id", unique = true)
	public String email_id;
	
	@NotBlank(message = "Invalid designation: Empty designation")
	@NotNull(message = "Invalid designation: designation is NULL")
	@Pattern(regexp="(?:admin|ADMIN|Admin|doctor|DOCTOR|Doctor|pharmacist|PHARMACIST|Pharmacist|receptionist|RECEPTIONIST|Receptionist|Other|OTHER|other)$",message = "Invalid designation : designation must be one of the following.. admin,doctor,pharmacist,receptionist,other,")
	@Column(name="designation")
	private String designation;
	
	@NotBlank(message = "Invalid password: Empty password")
	@NotNull(message = "Invalid password: password is NULL")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "Invalid password.. Password must contain at least one digit [0-9]. " + 
			"Password must contain at least one lowercase Latin character [a-z]. " + 
			"Password must contain at least one uppercase Latin character [A-Z]. " + 
			"Password must contain at least one special character like ! @ # & ( ). " + 
			"Password must contain a length of at least 8 characters and a maximum of 20 characters.")
	@Column(name="password")
	private String password;
	
	@NotBlank(message = "Invalid gender: Empty gender")
	@NotNull(message = "Invalid gender: gender is NULL")
	@Pattern(regexp="(?:male|Male|female|Female|FEMALE|MALE)$",message = "Invalid gender: Gender Must be either Male or Female")
	@Column(name="gender")
	private String gender;
	
}
