package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DoctorService;

import java.util.Iterator;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    private final AppointmentRepository appointmentRepository;
    @Override
    public Doctor saveDoctor(Doctor doctor) {
        Doctor doctor1= new Doctor();
        doctor1.setId(doctor.getId());
        doctor1.setFirstName(doctor.getFirstName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setPosition(doctor.getPosition());
        doctor1.setEmail(doctor.getEmail());
        doctor1.setHospital(hospitalRepository.getHospitalById(doctor.getHospitalId()));
        return doctorRepository.saveDoctor(doctor1);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.getDoctorById(id);
    }
    @Transactional
    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.getDoctorById(id);
        List<Appointment> appointments = doctor.getHospital().getAppointments();
        if ( appointments != null){
            Iterator<Appointment> iterator = appointments.iterator();
            while (iterator.hasNext()){
                Appointment appointment = iterator.next();
                if (appointment.getDoctor() != null && appointment.getDoctor().getId().equals(id)){
                    iterator.remove();
                    appointmentRepository.deleteAppointmentForDoctor(appointment.getId());
                }
            }
        }doctorRepository.deleteDoctor(id);

    }

    @Override
    public void updateDoctor(Long id, Doctor update,Long hospitalId) {
        doctorRepository.updateDoctor(id,update,hospitalId);

    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }

    @Override
    public List<Department> getAllDoctorDepartments(Long doctorId) {
        return doctorRepository.getAllDoctorDepartments(doctorId);
    }

    @Override
    public List<Appointment> getAllDoctorAppointments(Long doctorId) {
        return doctorRepository.getAllDoctorAppointments(doctorId);
    }

    @Override
    public String assignDoctorToDepartment(Long doctorId, Long departmentId) {
        return doctorRepository.assignDoctorToDepartment(doctorId,departmentId);
    }

    @Override
    public String assignDoctorToAppointment(Long doctorId, Long appointmentId) {
        return doctorRepository.assignDoctorToAppointment(doctorId,appointmentId);
    }
}
