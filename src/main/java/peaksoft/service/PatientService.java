package peaksoft.service;

import peaksoft.model.Patient;

import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient);

    Patient getPatientById(Long id);

    void deletePatient(Long id);

    void updatePatient(Long id, Patient update, Long hospitalId);

    List<Patient> getAllPatients();


    }
