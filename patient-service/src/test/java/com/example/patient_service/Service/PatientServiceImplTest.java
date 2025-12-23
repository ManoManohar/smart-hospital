package com.example.patient_service.Service;

import com.example.patient_service.ExceptionHandler.ResourceNotFoundException;
import com.example.patient_service.Model.Patient;
import com.example.patient_service.Repository.PatientRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepo patientRepo;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    void setUp() {
        patient1 = new Patient();
        patient1.setId(1L);
        patient1.setName("John Doe");
        patient1.setAge(30);
        patient1.setContactNumber("1234567890");
        patient1.setMedicalHistory("None");

        patient2 = new Patient();
        patient2.setId(2L);
        patient2.setName("Jane Smith");
        patient2.setAge(25);
        patient2.setContactNumber("9876543210");
        patient2.setMedicalHistory("Asthma");
    }


    // ---------------- SAVE ----------------

    @Test
    void savePatient_success() {
        when(patientRepo.save(any(Patient.class))).thenReturn(patient1);

        Patient saved = patientService.savePatient(patient1);

        assertNotNull(saved);
        assertEquals("John Doe", saved.getName());
        verify(patientRepo).save(any(Patient.class));
    }

    // ---------------- GET ALL ----------------

    @Test
    void getAllPatients_success() {
        when(patientRepo.findAll()).thenReturn(List.of(patient1, patient2));

        List<Patient> result = patientService.getAllPatients();

        assertEquals(2, result.size());
        verify(patientRepo).findAll();
    }

    // ---------------- GET BY ID ----------------

    @Test
    void getPatientById_success() {
        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient1));

        Optional<Patient> result = patientService.getPatientById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(patientRepo).findById(1L);
    }

    @Test
    void getPatientById_notFound() {
        when(patientRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> patientService.getPatientById(1L));

        verify(patientRepo).findById(1L);
    }

    // ---------------- DELETE ----------------

    @Test
    void deletePatient_success() {
        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient1));

        assertDoesNotThrow(() -> patientService.deletePatient(1L));

        verify(patientRepo).delete(patient1);
    }

    @Test
    void deletePatient_notFound() {
        when(patientRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> patientService.deletePatient(1L));

        verify(patientRepo).findById(1L);
        verify(patientRepo, never()).delete(any());
    }

    // ---------------- UPDATE ----------------

    @Test
    void updatePatient_success() {
        Patient updatedDetails = new Patient();
        updatedDetails.setName("John Updated");
        updatedDetails.setAge(35);
        updatedDetails.setContactNumber("789 Oak St");

        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient1));
        when(patientRepo.save(any(Patient.class))).thenReturn(patient1);

        Patient result = patientService.updatePatient(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("John Updated", result.getName());
        assertEquals(35, result.getAge());
        assertEquals("789 Oak St", result.getContactNumber());

        verify(patientRepo).save(any(Patient.class));
    }

    @Test
    void updatePatient_notFound() {
        when(patientRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> patientService.updatePatient(1L, new Patient()));

        verify(patientRepo).findById(1L);
        verify(patientRepo, never()).save(any());
    }
}
