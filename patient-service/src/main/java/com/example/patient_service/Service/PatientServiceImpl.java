package com.example.patient_service.Service;

import com.example.patient_service.ExceptionHandler.ResourceNotFoundException;
import com.example.patient_service.Model.Patient;
import com.example.patient_service.Repository.PatientRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PatientServiceImpl implements PatientService{
    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepo patientRepo;

    public PatientServiceImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public Patient savePatient(Patient patient) {
        logger.info("Adding new patient with name: {}", patient.getName());
       return patientRepo.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepo.findAll();
        logger.info("Returning all patients, count: {}", patients.size());
        return patients;
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        logger.info("getting patient with id: {}", id);
        return patientRepo.findById(id)
                .or(() -> {
                    throw new ResourceNotFoundException("Patient not found with id " + id);
                });
    }


    @Override
    public void deletePatient(Long id) {
        logger.info("Deleting patient with id: {}", id);
        patientRepo.deleteById(id);
    }

    @Override
    public Patient updatePatient(Long id, Patient patientDetails) {
        logger.info("Updating patient with id: {}", id);
        return patientRepo.findById(id).map(patient -> {
            patient.setName(patientDetails.getName());
            patient.setAge(patientDetails.getAge());
            patient.setContactNumber(patientDetails.getContactNumber());
            patient.setMedicalHistory(patientDetails.getMedicalHistory());
            Patient updated = patientRepo.save(patient);
            logger.info("Updated patient with id: {}", updated.getId());
            return updated;
        }).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }
}
