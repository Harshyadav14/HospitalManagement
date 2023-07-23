package com.HospitalManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.model.User;

@Repository
public interface AdminRepo extends JpaRepository<User, Integer> {
	
	@Query(value="SELECT * FROM user WHERE designation=:designation",nativeQuery=true)
	public List<User> findUserByDesignation(@Param("designation")String designation);
	
	@Query(value="SELECT * FROM user WHERE email_id=:email_id and password=:password",nativeQuery=true)
	public User findUserByEmailAndPassword(@Param("email_id")String email_id,@Param("password")String password);
	
	
	@Query(value="SELECT sum(treatment_bill) FROM appointment",nativeQuery=true)
	public Integer findTotalEarningFromAppointment();
	
	@Query(value="SELECT sum(total_expenses) FROM patients",nativeQuery=true)
	public Integer findTotalEarningFromPatients();
	
	
}
