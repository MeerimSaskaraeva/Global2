package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Department;
import peaksoft.repository.DepartmentRepository;
import peaksoft.service.DepartmentService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        Department department1 = new Department();
        department1.setId(department.getId());
        department1.setName(department.getName());
        return departmentRepository.saveDepartment(department1);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.getDepartmentById(id);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteDepartment(id);
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
