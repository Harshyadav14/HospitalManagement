package com.HospitalManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.model.Doctor;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer>{
	
	@Query(value="SELECT * FROM doctor WHERE name=:name",nativeQuery=true)
	public Doctor findDoctorByName(@Param("name")String name);

}
