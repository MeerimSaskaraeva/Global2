package peaksoft.service;

import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    Doctor getDoctorById(Long id);
    void deleteDoctor(Long id);
    void updateDoctor(Long id,Doctor update,Long hospitalId);
    List<Doctor> getAllDoctors();

    List<Department> getAllDoctorDepartments(Long doctorId);
    public List<Appointment> getAllDoctorAppointments(Long doctorId);
    String assignDoctorToDepartment(Long doctorId, Long departmentId);
    String assignDoctorToAppointment(Long doctorId, Long appointmentId);
}
