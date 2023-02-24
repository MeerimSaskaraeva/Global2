package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.Gender;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.PatientRepository;

import java.util.List;

@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Patient savePatient(Patient patient) {
        entityManager.persist(patient);
        return patient;
    }

    @Override
    public Patient getPatientById(Long id) {
        return entityManager.find(Patient.class,id);
    }

    @Override
    public void deletePatient(Long id) {
        entityManager.remove(entityManager.find(Patient.class,id));

    }

    @Override
    public void updatePatient(Long id, Patient update, Long hospitalId) {
        Patient patient=getPatientById(id);
        patient.setFirstName(update.getFirstName());
        patient.setLastName(update.getLastName());
        patient.setPhoneNumber(update.getPhoneNumber());
        patient.setGender(Gender.valueOf(update.getGender().toString()));
        patient.setEmail(update.getEmail());
        Hospital hospital=entityManager.find(Hospital.class,hospitalId);
        patient.setHospital(hospital);

    }

    @Override
    public List<Patient> getAllPatients() {
        return entityManager.createQuery("select p from Patient p",Patient.class).getResultList();
    }
}
