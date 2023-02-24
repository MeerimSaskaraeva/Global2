package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.HospitalRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class HospitalRepositoryImpl implements HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Hospital saveHospital(Hospital hospital) {
        entityManager.persist(hospital);
        return hospital;
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return entityManager.find(Hospital.class, id);
    }

    @Override
    public void deleteHospital(Long id) {
        entityManager.remove(entityManager.find(Hospital.class, id));


    }

    @Override
    public void updateHospital(Long id, Hospital update) {
        Hospital hospital = getHospitalById(id);
        hospital.setName(update.getName());
        hospital.setAddress(update.getAddress());

    }

    @Override
    public List<Hospital> getAllHospitals() {
        return entityManager.createQuery(
                "select h from Hospital h", Hospital.class).getResultList();
    }

    @Override
    public List<Doctor> getAllHospitalDoctor(Long hospitalId) {
        return entityManager.createQuery(
                        "select d from Doctor d join d.hospital hos where hos.id = :id", Doctor.class)
                .setParameter("id", hospitalId)
                .getResultList();
    }

    @Override
    public List<Patient> getAllHospitalPatients(Long hospitalId) {
        return entityManager.createQuery(
                "select p from Patient p join p.hospital hos where hos.id=:id ", Patient.class)
                .setParameter("id",hospitalId)
                .getResultList();
    }
    @Override
    public String assignHospitalToAppointment(Long hospitalId, Long appointmentId) {
        Hospital hospital=entityManager.find(Hospital.class,hospitalId);
        Appointment appointment=entityManager.find(Appointment.class,appointmentId);
//        List<Hospital>hospitals=new ArrayList<>(Arrays.asList(hospital));
        List<Appointment>appointments=new ArrayList<>(Arrays.asList(appointment));
//        hospital.setAppointments(appointments);
        hospital.getAppointments().add(appointment);
        return " ";
    }

    @Override
    public List<Appointment> getAllHospitalAppointments(Long hospitalId) {
        return entityManager.createQuery(
                "select a from Appointment a ,Hospital h where a.id =:id",
                Appointment.class).setParameter("id",hospitalId).getResultList();

    }
}
