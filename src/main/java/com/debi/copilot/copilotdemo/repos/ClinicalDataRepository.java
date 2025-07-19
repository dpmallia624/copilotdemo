package com.debi.copilot.copilotdemo.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import com.debi.copilot.copilotdemo.models.ClinicalData;
import java.util.List;

/**
 * Repository interface for managing ClinicalData entities.
 * This interface extends JpaRepository to provide CRUD operations and custom query methods.
 */
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find clinical data by component name:
    List<ClinicalData> findByComponentName(String componentName);
    
    // To find clinical data by patient ID:
    List<ClinicalData> findByPatientId(Long patientId);

}
