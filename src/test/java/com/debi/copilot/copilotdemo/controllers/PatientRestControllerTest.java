package com.debi.copilot.copilotdemo.controllers;

import com.debi.copilot.copilotdemo.models.Patient;
import com.debi.copilot.copilotdemo.repos.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PatientRestControllerTest {

    private PatientRepository patientRepository;
    private PatientRestController patientRestController;

    @BeforeEach
    public void setUp() {
        patientRepository = mock(PatientRepository.class);
        patientRestController = new PatientRestController(patientRepository);
    }

    @Test
    public void testGetAllPatients_ReturnsAllPatients() {
        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setFirstName("John");
        patient1.setLastName("Doe");

        Patient patient2 = new Patient();
        patient2.setId(2L);
        patient2.setFirstName("Jane");
        patient2.setLastName("Smith");

        List<Patient> patients = Arrays.asList(patient1, patient2);
        when(patientRepository.findAll()).thenReturn(patients);

        Iterable<Patient> result = patientRestController.getAllPatients();

        assertNotNull(result);
        assertTrue(result instanceof List);
        assertEquals(2, ((List<?>) result).size());
        assertEquals("John", ((List<Patient>) result).get(0).getFirstName());
        assertEquals("Smith", ((List<Patient>) result).get(1).getLastName());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllPatients_ReturnsEmptyList() {
        when(patientRepository.findAll()).thenReturn(Arrays.asList());

        Iterable<Patient> result = patientRestController.getAllPatients();

        assertNotNull(result);
        assertTrue(result instanceof List);
        assertEquals(0, ((List<?>) result).size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testCreatePatient_Success() {
        Patient patient = new Patient();
        patient.setFirstName("Alice");
        patient.setLastName("Wonderland");

        Patient savedPatient = new Patient();
        savedPatient.setId(10L);
        savedPatient.setFirstName("Alice");
        savedPatient.setLastName("Wonderland");

        when(patientRepository.save(patient)).thenReturn(savedPatient);

        Patient result = patientRestController.createPatient(patient);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Alice", result.getFirstName());
        assertEquals("Wonderland", result.getLastName());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testCreatePatient_NullPatient() {
        when(patientRepository.save(null)).thenReturn(null);

        Patient result = patientRestController.createPatient(null);

        assertNull(result);
        verify(patientRepository, times(1)).save(null);
    }
}