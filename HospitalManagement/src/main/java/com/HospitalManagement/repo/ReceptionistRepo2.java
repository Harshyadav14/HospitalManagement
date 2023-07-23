package com.HospitalManagement.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.model.Appointment;

@Repository
public interface ReceptionistRepo2 extends JpaRepository<Appointment, Integer>{

}
