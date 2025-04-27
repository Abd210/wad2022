package com.example.data.repositories;

import com.example.data.domain.CareProvider;
import com.example.data.domain.MedicalEncounter;
import com.example.data.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface MedicalEncounterRepository extends JpaRepository<MedicalEncounter, Long> {

    // Given a date -> all patients with an encounter on that date.
    @Query("SELECT DISTINCT me.patient FROM MedicalEncounter me WHERE me.date = :date")
    List<Patient> findPatientsByEncounterDate(@Param("date") LocalDate date);

    // Given health issue type -> all care providers who performed a health service for that health issue.
    @Query("SELECT DISTINCT me.careProvider " +
            "FROM MedicalEncounter me " +
            "JOIN me.healthServices hs " +
            "JOIN hs.healthIssue hi " +
            "WHERE hi.type = :issueType")
    List<CareProvider> findCareProvidersByHealthIssueType(@Param("issueType") String issueType);
}
