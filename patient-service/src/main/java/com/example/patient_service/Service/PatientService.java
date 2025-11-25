package com.example.patient_service.Service;

import com.example.patient_service.Model.Patient;

import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);

    void deletePatient(Long id);

    Patient updatePatient(Long id, Patient patient);
}
