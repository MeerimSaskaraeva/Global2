package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DepartmentService;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public Department saveDepartment(Department department,Long hospitalId) {
        Department department1 = new Department();
        department1.setId(department.getId());
        department1.setName(department.getName());
        department1.setHospitalId(hospitalId);
        department1.setHospital(hospitalRepository.getHospitalById(department.getHospitalId()));
        return departmentRepository.saveDepartment(department1,hospitalId);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.getDepartmentById(id);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.getDepartmentById(id);
        Hospital hospital=department.getHospital();

        List<Appointment> appointments = department.getHospital().getAppointments();
        if ( appointments != null){
            Iterator<Appointment> iterator = appointments.iterator();
            while (iterator.hasNext()){
                Appointment appointment = iterator.next();
                if (appointment.getDepartment() != null && appointment.getDepartment().getId().equals(id)){
                    iterator.remove();
                    appointmentRepository.deleteAppointmentForDoctor(appointment.getId());
                }
            }
        }departmentRepository.deleteDepartment(id);

    }

    @Override
    public void deleteDepartment2(Long id) {
        departmentRepository.deleteDepartment2(id);
    }

    @Override
    public void updateDepartment(Long id, Department update) {
        departmentRepository.updateDepartment(id, update);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }
}
