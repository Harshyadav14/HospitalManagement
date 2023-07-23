package com.HospitalManagement.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagement.model.Doctor;
import com.HospitalManagement.model.User;
import com.HospitalManagement.repo.DoctorRepo;
import com.HospitalManagement.repo.AdminRepo;
import com.HospitalManagement.service.AdminService;

@RestController
public class AdminController 
{
	
	@Autowired
	AdminService userservice;
	
	@Autowired
	AdminRepo userrepo;
	
	@Autowired
	DoctorRepo doctorrepo;
	
	@GetMapping("/Check")
	public String check() {
		return "Hi Harsh Project is Working";
	}
	
	@PostMapping("/Signup")
	public User AddUser(@Valid @RequestBody User user) {
		userservice.AddUser(user);
		return user;
	}

	@GetMapping("/View_Users")
	public List<User> showAllUsers() {
		return userservice.showAllUsers();
	}
	
	@GetMapping("/findUserBy_UserId/{user_id}")
	public Optional<User> findRegById(@Valid @PathVariable int user_id) {
		return userservice.findUserById(user_id);
	}
	
	@GetMapping("/findUserListBy_Designation/{designation}")
	public List<User> findUserListByDesignation(@Valid @PathVariable String designation) {
		return userservice.findUserByDesignation(designation);
	}
	
	@PutMapping("/Update_User")
	public String update(@Valid @RequestBody User user) {
		userservice.updateUser(user);
		return "User With userId "+user.getUser_id()+" has been Updated successfully";
	}
	
	@DeleteMapping("/DeleteUserBy_UserId/{user_id}")
	public String delete(@Valid @PathVariable int user_id) {
		userservice.deleteUser(user_id);
		return "User With userId "+user_id+" has been Deleted successfully";
	}
	

	@GetMapping("/TotalEarning")
	public String findTotalEarning() {
		return userservice.findTotalEarning();
	}
	
	@GetMapping("/Login")
	public String Login(HttpServletRequest request)
	{
		String response="User Not Verified";
		try {
			String header=request.getHeader("Authorization"); //Returns the value of the specified request header as a string.
			
			User user=userservice.loginStatusVerify(header);
			
			if(user!=null) {
				response="Verified User";
			}
		}
		catch(Exception ex) {
		}
		return response;	
	}

}
