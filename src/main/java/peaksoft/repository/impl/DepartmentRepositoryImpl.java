package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.repository.DepartmentRepository;

import java.util.List;

@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department saveDepartment(Department department,Long hospitalId) {
        entityManager.persist(department);
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        hospital.getDepartments().add(department);
        return department;
    }

    @Override
    public Department getDepartmentById(Long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = entityManager.find(Department.class, id);
        entityManager.remove(department);
//        Query nativeQuery = entityManager.createNativeQuery(
//                "delete from doctors_departments where departments_id=" + id);
//        nativeQuery.executeUpdate();
//        entityManager.remove( entityManager.find(Department.class,id));

    }

    @Override
    public void deleteDepartment2(Long id) {
        Query nativeQuery = entityManager.createNativeQuery(
                "delete from appointments where departments_id=" + id);
        nativeQuery.executeUpdate();
        entityManager.remove( entityManager.find(Department.class,id));

    }


    @Override
    public void updateDepartment(Long id, Department update) {
        Department department = getDepartmentById(id);
        department.setName(update.getName());
    }

    @Override
    public List<Department> getAllDepartments() {
        return entityManager.createQuery(
                "select d from Department d", Department.class).getResultList();
    }
}
