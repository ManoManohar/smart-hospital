package com.example.patient_service.Controller;

import com.example.patient_service.Model.Patient;
import com.example.patient_service.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<Patient> save(@RequestBody Patient patient) {
        Patient savedPatient = service.savePatient(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<List<Patient>> getAll() {
        List<Patient> patients = service.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable Long id) {
        // The service throws ResourceNotFoundException if not found, handled globally
        Optional<Patient> patient = service.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        // Service throws exception if patient not found
        Patient updatedPatient = service.updatePatient(id, patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        // Exception if patient not found handled by service/global exception handler
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
