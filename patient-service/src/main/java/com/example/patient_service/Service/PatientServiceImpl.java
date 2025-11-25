package com.example.patient_service.Service;

import com.example.patient_service.Model.Patient;
import com.example.patient_service.Repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepo patientRepo;

    public PatientServiceImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public Patient savePatient(Patient patient) {
       return patientRepo.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepo.findById(id).orElse(null);
    }
}
