package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;

@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Hospital saveHospital(Hospital hospital) {
        return hospitalRepository.saveHospital(hospital);
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.getHospitalById(id);
    }

    @Override
    public void deleteHospital(Long id) {
        hospitalRepository.deleteHospital(id);
    }

    @Override
    public void updateHospital(Long id, Hospital update) {
        hospitalRepository.updateHospital(id,update);

    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.getAllHospitals();
    }

    @Override
    public List<Doctor> getAllHospitalDoctor(Long hospitalId) {
        return hospitalRepository.getAllHospitalDoctor(hospitalId);
    }

    @Override
    public List<Patient> getAllHospitalPatients(Long hospitalId) {
        return hospitalRepository.getAllHospitalPatients(hospitalId);
    }

    @Override
    public String assignHospitalToAppointment(Long hospitalId, Long appointmentId) {
        return hospitalRepository.assignHospitalToAppointment(hospitalId,appointmentId);
    }

    @Override
    public List<Appointment> getAllHospitalAppointments(Long hospitalId) {
        return hospitalRepository.getAllHospitalAppointments(hospitalId);
    }
}
