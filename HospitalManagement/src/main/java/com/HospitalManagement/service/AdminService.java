package com.HospitalManagement.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalManagement.model.Doctor;
import com.HospitalManagement.model.User;
import com.HospitalManagement.repo.DoctorRepo;
import com.HospitalManagement.repo.AdminRepo;

@Service
public class AdminService {
	
	@Autowired
	AdminRepo userrepo;
	
	@Autowired
	DoctorRepo doctorrepo;
	
	public void AddUser(User user) 
	{
		userrepo.save(user);
		AddDoctor(user);
	}
	
	public void AddDoctor(User user)
	{
		Doctor doctor = new Doctor();
		String designation=user.getDesignation();
		if(designation.toLowerCase().equals("doctor"))
		{
			doctor.setUser_id(user.getUser_id());
			doctor.setName(user.getUser_name());
			doctor.setEmail(user.getEmail_id());
			doctor.setPhoneno(user.getPhone_number());
			doctor.setGender(user.getGender());
			doctorrepo.saveAndFlush(doctor);
		}
	}
	
	public List<User> showAllUsers()
	{
		return userrepo.findAll();
	}
	
	public Optional<User> findUserById(int id) {
		return userrepo.findById(id);
	}
	
	public List<User> findUserByDesignation(String designation) {
		List<User> userList=userrepo.findUserByDesignation(designation);
		return userList;
	}
	
	public void updateUser(User user) {
		userrepo.saveAndFlush(user);
		AddDoctor(user);
	}
	
	public void deleteUser(int userId) {
		User user=(User)userrepo.findById(userId).get();
		String designation=user.getDesignation();
		if(designation.toLowerCase().equals("doctor"))
		{
			Doctor doctor = (Doctor)doctorrepo.findById(userId).get();
			doctorrepo.deleteById(doctor.getUser_id());
		}
		userrepo.deleteById(userId);
	}

	public User loginStatusVerify(String header) {
		String[] arrOfstr=header.split(" ");
		String encode= arrOfstr[1];
		byte[] actualByte=Base64.getDecoder().decode(encode);
		String actualString=new String(actualByte);
		String[] userLoginCredentials=actualString.split(":");
		String emailId=userLoginCredentials[0];
		//System.out.println("emailId="+emailId);
		String password=userLoginCredentials[1];
		//System.out.println("password"+password);
		User user=userrepo.findUserByEmailAndPassword(emailId, password);
		//System.out.println("user"+user);
		return user;
	}

	public String findTotalEarning() {
		int totalEarningOPD=userrepo.findTotalEarningFromAppointment();
		int totalEarningIPD=userrepo.findTotalEarningFromPatients();
		int total=totalEarningOPD+totalEarningIPD;
		return "Total Earning is Rs."+total+" From OPD Rs."+totalEarningOPD+" and From IPD Rs."+totalEarningIPD;
	}
}
