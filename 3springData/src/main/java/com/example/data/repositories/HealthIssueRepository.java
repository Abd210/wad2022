package com.example.data.repositories;

import com.example.data.domain.HealthIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HealthIssueRepository extends JpaRepository<HealthIssue, Long> {

    // Given patient ID -> all health issues
    @Query("SELECT hi FROM HealthIssue hi WHERE hi.patient.id = :patientId")
    List<HealthIssue> findByPatientId(@Param("patientId") Long patientId);

    // Given patient ID -> only health issues with at least one service.
    @Query("SELECT hi FROM HealthIssue hi WHERE hi.patient.id = :patientId AND size(hi.healthServices) > 0")
    List<HealthIssue> findIssuesWithServiceByPatientId(@Param("patientId") Long patientId);
}
