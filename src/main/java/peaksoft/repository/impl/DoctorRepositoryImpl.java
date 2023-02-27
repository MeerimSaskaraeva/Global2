package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.model.*;
import peaksoft.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class DoctorRepositoryImpl implements DoctorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        entityManager.persist(doctor);
        return doctor;
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return entityManager.find(Doctor.class, id);
    }

    @Transactional
    @Override
    public void deleteDoctor(Long id) {
        entityManager.remove(entityManager.find(Doctor.class, id));

    }

    @Override
    public void updateDoctor(Long id, Doctor update) {
        Doctor doctor = getDoctorById(id);
        doctor.setFirstName(update.getFirstName());
        doctor.setLastName(update.getLastName());
        doctor.setPosition(update.getPosition());
        doctor.setEmail(update.getEmail());


    }

    @Override
    public List<Doctor> getAllDoctors() {
        return entityManager.createQuery("select d from Doctor d", Doctor.class).getResultList();
    }

    @Override
    public List<Department> getAllDoctorDepartments(Long doctorId) {
        return entityManager.createQuery(
                "select d from Department d join d.doctors doc where doc.id=:id ",
                Department.class).setParameter("id", doctorId).getResultList();
    }

    @Override
    public List<Appointment> getAllDoctorAppointments(Long doctorId) {
        return entityManager.createQuery(
                "select a from Appointment a join a.doctor doc where doc.id=:id",
                Appointment.class).setParameter("id", doctorId).getResultList();
    }

    @Override
    public String assignDoctorToDepartment(Long hospitalId,Long doctorId,Long departmentId) {
        List<Doctor> doctors = entityManager.createQuery("select d from Doctor d where d.hospital.id=:hospitalId", Doctor.class)
                .setParameter("id", hospitalId).getResultList();
        List<Department> departments = entityManager.createQuery("select d from Department d where d.hospital.id=:id", Department.class)
                .setParameter("id", hospitalId).getResultList();
        for (Doctor doctor : doctors) {
            doctor.setDepartments(departments);
        }
        return "";
    }

    @Override
    public String assignDoctorToAppointment(Long doctorId, Long appointmentId) {
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        Appointment appointment = entityManager.find(Appointment.class, appointmentId);
        List<Appointment> appointments = new ArrayList<>(Arrays.asList(appointment));
        doctor.getAppointments().add(appointment);
        return " ";
    }
    @Override
    public Long getDepartmentsByDoctorId(Long doctorId) {
        Long id = entityManager.createQuery(
                "select d.hospital.id from Doctor d where d.id=:doctorId",
                Hospital.class).setParameter("id", doctorId).getSingleResult().getId();

        return id;
    }
}
