package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.*;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;

@Service
@Transactional

public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    public HospitalServiceImpl(HospitalRepository hospitalRepository, DoctorRepository doctorRepository) {
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
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
    public List<Department> getAllHospitalDepartments(Long hospitalId) {
        return hospitalRepository.getAllHospitalDepartments(hospitalId);
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
