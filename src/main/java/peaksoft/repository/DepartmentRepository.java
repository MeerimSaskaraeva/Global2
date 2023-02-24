package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.model.Doctor;

import java.util.List;

@Repository
public interface DepartmentRepository {
    Department saveDepartment(Department department);
    Department getDepartmentById(Long id);
    void deleteDepartment(Long id);
    public void deleteDepartment2(Long id);
    void updateDepartment(Long id,Department update);
    List<Department> getAllDepartments();
}
