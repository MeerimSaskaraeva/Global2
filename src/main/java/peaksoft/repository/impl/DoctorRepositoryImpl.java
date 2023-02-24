package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.*;
import peaksoft.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
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
    public void updateDoctor(Long id, Doctor update, Long hospitalId) {
        Doctor doctor = getDoctorById(id);
        doctor.setFirstName(update.getFirstName());
        doctor.setLastName(update.getLastName());
        doctor.setPosition(update.getPosition());
        doctor.setEmail(update.getEmail());
        Hospital hospital1 = entityManager.find(Hospital.class, hospitalId);
        doctor.setHospital(hospital1);

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
    public String assignDoctorToDepartment(Long doctorId, Long departmentId) {
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        Department department = entityManager.find(Department.class, departmentId);
        List<Doctor> doctors = new ArrayList<>(Arrays.asList(doctor));
        List<Department> departments = new ArrayList<>(Arrays.asList(department));
        doctor.getDepartments().add(department);
        return " ";
    }

    @Override
    public String assignDoctorToAppointment(Long doctorId, Long appointmentId) {
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        Appointment appointment = entityManager.find(Appointment.class, appointmentId);
//        List<Doctor>doctors=new ArrayList<>(Arrays.asList(doctor));
        List<Appointment> appointments = new ArrayList<>(Arrays.asList(appointment));
        doctor.getAppointments().add(appointment);

        return " ";
    }
}
