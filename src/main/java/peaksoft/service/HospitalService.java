package peaksoft.service;

import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;

import java.util.List;

public interface HospitalService {
    Hospital saveHospital(Hospital hospital);
    Hospital getHospitalById(Long id);
    void deleteHospital(Long id);
    void updateHospital(Long id,Hospital update);
    List<Hospital> getAllHospitals();
    List<Doctor> getAllHospitalDoctor(Long hospitalId);
    List<Patient> getAllHospitalPatients(Long hospitalId);

    public String assignHospitalToAppointment(Long hospitalId, Long appointmentId);
    public List<Appointment> getAllHospitalAppointments(Long hospitalId);
}
