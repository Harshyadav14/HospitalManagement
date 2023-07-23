package com.HospitalManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.model.Patient;

@Repository
public interface ReceptionistRepo extends JpaRepository<Patient, Integer>{
	
	@Query(value="SELECT * FROM patients WHERE admit_date=:admit_date",nativeQuery=true)
	public List<Patient> findAllpatientsByAdmitDate(@Param("admit_date")String admit_date);

	@Query(value="SELECT * FROM patients WHERE discharge_date=:discharge_date",nativeQuery=true)
	public List<Patient> findAllpatientsByDischargeDate(@Param("discharge_date")String discharge_date);

	@Query(value="SELECT * FROM patients WHERE ipd_status=:ipd_status",nativeQuery=true)
	public List<Patient> findAllpatientsByStatus(@Param("ipd_status")String ipd_status);
	
	@Query(value="SELECT * FROM patients WHERE blood_group=:blood_group",nativeQuery=true)
	public List<Patient> findAllpatientsByBloodGroup(@Param("blood_group")String blood_group);

}
