package com.example.data.controllers;

import com.example.data.domain.CareProvider;
import com.example.data.domain.HealthIssue;
import com.example.data.domain.Patient;
import com.example.data.repositories.MedicalEncounterRepository;
import com.example.data.repositories.PatientRepository;
import com.example.data.repositories.HealthIssueRepository;
import com.example.data.repositories.CareProviderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/queries")
public class QueryController {

    private final HealthIssueRepository healthIssueRepository;
    private final MedicalEncounterRepository medicalEncounterRepository;
    private final PatientRepository patientRepository;
    private final CareProviderRepository careProviderRepository;

    public QueryController(HealthIssueRepository healthIssueRepository,
                           MedicalEncounterRepository medicalEncounterRepository,
                           PatientRepository patientRepository,
                           CareProviderRepository careProviderRepository) {
        this.healthIssueRepository = healthIssueRepository;
        this.medicalEncounterRepository = medicalEncounterRepository;
        this.patientRepository = patientRepository;
        this.careProviderRepository = careProviderRepository;
    }

    @GetMapping
    public String showQueriesPage(Model model) {
        return "queries"; // This looks for src/main/resources/templates/queries.html
    }

    // Query 1
    @PostMapping("/patientIssues")
    public String getPatientIssues(@RequestParam Long patientId,
                                   @RequestParam String action,
                                   Model model) {
        List<HealthIssue> issues = "issuesWithService".equals(action)
                ? healthIssueRepository.findIssuesWithServiceByPatientId(patientId)
                : healthIssueRepository.findByPatientId(patientId);

        model.addAttribute("resultColumns", List.of("ID", "Type"));
        model.addAttribute("results", issues.stream()
                .map(issue -> List.of(issue.getId().toString(), issue.getType()))
                .toList());

        return "queries";
    }

    // Query 2
    @PostMapping("/encounterDate")
    public String getPatientsByEncounterDate(@RequestParam String encounterDate, Model model) {
        LocalDate date = LocalDate.parse(encounterDate);
        List<Patient> patients = medicalEncounterRepository.findPatientsByEncounterDate(date);

        model.addAttribute("resultColumns", List.of("ID", "Name"));
        model.addAttribute("results", patients.stream()
                .map(p -> List.of(p.getId().toString(), p.getName()))
                .toList());

        return "queries";
    }

    // Query 3
    @PostMapping("/careProviderPatients")
    public String getPatientsByCareProvider(@RequestParam String providerName, Model model) {
        List<Patient> patients = careProviderRepository.findPatientsByCareProviderName(providerName);

        model.addAttribute("resultColumns", List.of("ID", "Name"));
        model.addAttribute("results", patients.stream()
                .map(p -> List.of(p.getId().toString(), p.getName()))
                .toList());

        return "queries";
    }

    // Query 4
    @PostMapping("/issueCareProviders")
    public String getCareProvidersByIssue(@RequestParam String issueType, Model model) {
        List<CareProvider> providers = medicalEncounterRepository.findCareProvidersByHealthIssueType(issueType);

        model.addAttribute("resultColumns", List.of("ID", "Name", "Specialty"));
        model.addAttribute("results", providers.stream()
                .map(cp -> List.of(cp.getId().toString(), cp.getName(), cp.getSpecialty()))
                .toList());

        return "queries";
    }
}
