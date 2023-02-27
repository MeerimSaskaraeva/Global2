package peaksoft.service;

import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    Doctor getDoctorById(Long id);
    void deleteDoctor(Long id);
    void updateDoctor(Long id,Doctor update);
    List<Doctor> getAllDoctors();

    List<Department> getAllDoctorDepartments(Long doctorId);
    public List<Appointment> getAllDoctorAppointments(Long doctorId);
    public String assignDoctorToDepartment(Long hospitalId,Long doctorId,Long departmentId);
    String assignDoctorToAppointment(Long doctorId, Long appointmentId);
    public Long getDepartmentsByDoctorId(Long doctorId);

}
