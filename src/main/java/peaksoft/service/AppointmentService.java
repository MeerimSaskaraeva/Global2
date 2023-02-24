package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Patient;

import java.util.List;

@Service
public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment,Long hospitalId);
    Appointment getAppointmentById(Long id);
    void deleteAppointmentForDoctor(Long id);
    void deleteAppointment(Long id);
    void updateAppointment(Long id,Appointment update,Long pId,Long depId,Long docId);
    List<Appointment> getAllAppointments();
    List<Appointment> getAllApps(String keyWord);
    public List<Patient> getAllAppointmentPatients(Long appId);
    public List<Department> getAllAppointmentDepartments(Long appId);
    public List<Doctor> getAllAppointmentDoctors(Long appId);


}
