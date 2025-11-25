package com.example.patient_service.Repository;

import com.example.patient_service.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Long> {
}
