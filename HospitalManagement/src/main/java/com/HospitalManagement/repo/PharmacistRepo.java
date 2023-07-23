package com.HospitalManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.model.Medicine;

@Repository
public interface PharmacistRepo extends JpaRepository<Medicine, Integer> {
	
	@Query(value="SELECT price FROM medicine WHERE med_id=:med_id",nativeQuery=true)
	public Integer findMedicinePriceFromId(@Param("med_id")String med_id);
	
	@Query(value="SELECT stock FROM medicine WHERE med_id=:med_id",nativeQuery=true)
	public Integer findMedicineStockFromId(@Param("med_id")String med_id);
	
}
