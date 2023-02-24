package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Patient;

import java.util.List;

@Repository
public interface AppointmentRepository {
    Appointment saveAppointment(Appointment appointment,Long hospitalId);
    Appointment getAppointmentById(Long id);
    void deleteAppointmentForDoctor(Long id);
    void deleteAppointment(Long id);
    void updateAppointment(Long id,Appointment update,Long pId,Long depId,Long docId);
    List<Appointment> getAllAppointments();
    public List<Patient> getAllAppointmentPatients(Long appId);
    public List<Department> getAllAppointmentDepartments(Long appId);
    public List<Doctor> getAllAppointmentDoctors(Long appId);


    List<Appointment> search(String keyWord);
}
