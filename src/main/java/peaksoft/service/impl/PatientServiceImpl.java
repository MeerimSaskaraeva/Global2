package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.Gender;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.model.Patient;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.Iterator;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;
    @Override
    public Patient savePatient(Patient patient) {
        Patient patient1=new Patient();
        patient1.setFirstName(patient.getFirstName());
        patient1.setLastName(patient.getLastName());
        patient1.setPhoneNumber(patient.getPhoneNumber());
        patient1.setGender(Gender.valueOf(patient.getGender().toString()));
        patient1.setEmail(patient.getEmail());
        patient1.setHospital(hospitalRepository.getHospitalById(patient.getHospitalId()));
        return patientRepository.savePatient(patient1);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.getPatientById(id);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.getPatientById(id);
        List<Appointment> appointments = patient.getHospital().getAppointments();
        if ( appointments != null){
            Iterator<Appointment> iterator = appointments.iterator();
            while (iterator.hasNext()){
                Appointment appointment = iterator.next();
                if (appointment.getPatient() != null && appointment.getPatient().getId().equals(id)){
                    iterator.remove();
                    appointmentRepository.deleteAppointmentForDoctor(appointment.getId());
                }
            }
        }patientRepository.deletePatient(id);

    }

    @Override
    public void updatePatient(Long id, Patient update) {
        patientRepository.updatePatient(id, update);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }

}
