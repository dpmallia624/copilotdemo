package com.debi.copilot.copilotdemo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.debi.copilot.copilotdemo.models.Patient;
import com.debi.copilot.copilotdemo.repos.PatientRepository;
import com.debi.copilot.copilotdemo.requests.PatientRequest;

@Service
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Async("asyncExecutor")
    public void process(PatientRequest patientRequest, Acknowledgment acknowledgment){
        LOGGER.info("Processing: {}", patientRequest.getFirstName()+","+patientRequest.getLastName());

        try {
            Patient patient = new Patient();
            patient.setAge(patientRequest.getAge());
            patient.setFirstName(patientRequest.getFirstName());
            patient.setLastName(patientRequest.getLastName());

            patientRepository.save(patient);
        } catch (Exception e) {
            LOGGER.error("Failed to process {}", patientRequest.getFirstName()+","+patientRequest.getLastName());
        } finally {
            acknowledgment.acknowledge();
        }
    }
}
