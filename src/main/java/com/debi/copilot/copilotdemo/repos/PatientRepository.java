package com.debi.copilot.copilotdemo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.debi.copilot.copilotdemo.models.Patient;
import java.util.List;

/**
 * Repository interface for managing Patient entities.
 * This interface extends JpaRepository to provide CRUD operations and custom query methods.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
   
}
