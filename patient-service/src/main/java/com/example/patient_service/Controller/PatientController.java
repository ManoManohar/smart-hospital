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
    public List<Patient> getAll() {
        return service.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = service.getPatientById(id);
        return ResponseEntity.ok(patient);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient updatedPatient = service.updatePatient(id, patient);
        if (updatedPatient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
