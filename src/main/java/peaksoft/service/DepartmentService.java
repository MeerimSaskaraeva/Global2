package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Department;

import java.util.List;

@Service
public interface DepartmentService {
    Department saveDepartment(Department department);
    Department getDepartmentById(Long id);
    void deleteDepartment(Long id);
    public void deleteDepartment2(Long id);
    void updateDepartment(Long id,Department update);
    List<Department> getAllDepartments();
}
