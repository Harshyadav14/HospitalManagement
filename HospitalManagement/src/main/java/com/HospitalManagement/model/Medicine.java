package com.HospitalManagement.model;

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
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicine")
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="med_id")
	private int med_id;
	
	@NotBlank(message = "Invalid Medicine Name: Blank")
    @NotNull(message = "Invalid Medicine Name:  NULL")
	@Size(min = 3, max = 30, message = "Invalid Medicine Name: Medicine Name Must be of 3 - 30 characters")
	@Column(name="name")
	private String name;
	
	@NotBlank(message = "Invalid Medicine desc: Blank")
    @NotNull(message = "Invalid Medicine desc:  NULL")
	@Size(min = 3, max = 400, message = "Invalid Medicine description: description Must be of 3 - 400 characters")
	@Column(name="details")
	public String details;
	
	
	@Min(value = 1, message = "Invalid medicine stock : Equals to zero or Less than zero")
    @Max(value =1000 , message = "Invalid medicine stock : Exceeds Rs.1000")
	@Column(name="stock")
	public int stock;
	
	@Min(value = 1, message = "Invalid price : Equals to zero or Less than zero")
    @Max(value =10000 , message = "Invalid price : Exceeds Rs.10000")
	@Column(name="price")
	private int price;
	
}
