package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.*;
import peaksoft.repository.*;
import peaksoft.service.AppointmentService;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment, Long hospitalId) {
        Appointment appointment1 = new Appointment();
        appointment1.setId(appointment.getId());
        appointment1.setDate(appointment.getDate().plusDays(1));
        appointment1.setHospitalId(hospitalId);
        appointment1.setPatient(patientRepository.getPatientById(appointment.getPatientId()));
        appointment1.setDepartment(departmentRepository.getDepartmentById(appointment.getDepartmentId()));
        appointment1.setDoctor(doctorRepository.getDoctorById(appointment.getDoctorId()));
        return appointmentRepository.saveAppointment(appointment1, hospitalId);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.getAppointmentById(id);
    }

    @Override
    public void deleteAppointmentForDoctor(Long id) {
        appointmentRepository.deleteAppointmentForDoctor(id);

    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.getAppointmentById(id);
        Hospital hospital = appointment.getPatient().getHospital();
        hospital.getAppointments().clear();

        appointmentRepository.deleteAppointmentForDoctor(id);
    }

    @Override
    public void updateAppointment(Long id, Appointment update, Long pId, Long depId, Long docId) {
        appointmentRepository.updateAppointment(id, update, pId, depId, docId);

    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    @Override
    public List<Appointment> getAllApps(String keyWord) {
        if (keyWord != null && !keyWord.trim().isEmpty())
            return appointmentRepository.search(keyWord);
        else {
           return appointmentRepository.getAllAppointments();

        }

    }

    @Override
    public List<Patient> getAllAppointmentPatients(Long appId) {
        return appointmentRepository.getAllAppointmentPatients(appId);
    }

    @Override
    public List<Department> getAllAppointmentDepartments(Long appId) {
        return appointmentRepository.getAllAppointmentDepartments(appId);
    }

    @Override
    public List<Doctor> getAllAppointmentDoctors(Long appId) {
        return appointmentRepository.getAllAppointmentDoctors(appId);
    }


}
