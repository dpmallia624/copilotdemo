package com.debi.copilot.copilotdemo.controllers;

import com.debi.copilot.copilotdemo.models.Patient;
import com.debi.copilot.copilotdemo.repos.PatientRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/patients")
public class PatientRestController {

    final Logger logger = LoggerFactory.getLogger(PatientRestController.class);
    private final PatientRepository patientRepository;
    public PatientRestController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Handles HTTP GET requests to retrieve all patients.
     *
     * @return an {@link Iterable} containing all {@link Patient} entities from the repository
     */
    @GetMapping
    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Handles HTTP POST requests to create a new patient.
     *
     * @param patient the {@link Patient} entity to be created
     * @return the created {@link Patient} entity
     */
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        logger.atInfo().setMessage("Searching patient Id {}.").addArgument(id).log();
        logger.atInfo().setMessage("Patient changed.").addKeyValue("correlationId", id).log();

        return patientRepository.findById(id)
                .map(patient -> ResponseEntity.ok(patient))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) {
        logger.atInfo().setMessage("Patient with Id  {} and set age to {}. Old value was {}.").addArgument(id).addArgument(patient.getAge()).addArgument(patient.getAge()).log();

        if (patientRepository.existsById(id)) {
            patient.setId(id);
            Patient savedPatient = patientRepository.save(patient);
            return ResponseEntity.ok(savedPatient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
