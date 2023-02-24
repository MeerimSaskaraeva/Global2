package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.model.*;
import peaksoft.repository.AppointmentRepository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Appointment saveAppointment(Appointment appointment,Long hospitalId) {
        entityManager.persist(appointment);
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        hospital.getAppointments().add(appointment);
        return appointment;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return entityManager.find(Appointment.class, id);
    }

    @Override
    public void deleteAppointmentForDoctor(Long id) {
        Query nativeQuery = entityManager.createNativeQuery(
                "delete from  appointments  a where a.id=" + id);
        nativeQuery.executeUpdate();
        entityManager.remove(entityManager.find(Appointment.class,id));// ooooooooooooo

    }

    @Override
    public void deleteAppointment(Long id) {
//        Query nativeQuery = entityManager.createNativeQuery(
//                "delete from  appointments  a where a.id=" + id);
//        nativeQuery.executeUpdate();
        entityManager.remove(entityManager.find(Appointment.class,id));
    }

    @Override
    public void updateAppointment(Long id, Appointment update,Long pId,Long depId,Long docId) {
        Appointment appointment = getAppointmentById(id);
        appointment.setDate(update.getDate());
        appointment.setPatient(entityManager.find(Patient.class,pId));
        appointment.setDepartment(entityManager.find(Department.class,depId));
        appointment.setDoctor(entityManager.find(Doctor.class,docId));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return entityManager.createQuery("select a from Appointment a", Appointment.class)
                .getResultList();
    }

    @Override
    public List<Patient> getAllAppointmentPatients(Long appId) {
        return entityManager.createQuery(
                        "select p from Patient p join p.appointments app where app.id=:id ",
                        Patient.class)
                .setParameter("id", appId)
                .getResultList();
    }

    @Override
    public List<Department> getAllAppointmentDepartments(Long appId) {
        return entityManager.createQuery(
                        "select d from Department d join d.doctors doc join doc.departments dep " +
                                "where dep.id=:id ",
                        Department.class)
                .setParameter("id", appId)
                .getResultList();
    }

    @Override
    public List<Doctor> getAllAppointmentDoctors(Long appId) {
        return entityManager.createQuery(
                        "select d from Doctor d join d.appointments app where app.id=:id ",
                        Doctor.class)
                .setParameter("id", appId)
                .getResultList();
    }

    @Override
    public List<Appointment> search(String keyWord) {
        return entityManager.createQuery("select a from Appointment a where a.date =(:keyWord)",
                Appointment.class).setParameter("keyWord","%"+keyWord+"%")
                .getResultList();
    }


}
