package org.nicolaclemente.jpa.Repository;

import org.nicolaclemente.jpa.Entity.Employee;
import jakarta.persistence.EntityManager;

import java.util.Optional;

//isolate the persistence logic for each entity using the Repository pattern
public class EmployeeRepositoryImpl implements EmployeeRepository {
    EntityManager entityManager;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Employee> save(Employee employee) {
        try {
            entityManager.getTransaction().begin(); //uncomment if not using @Transactional
            if (employee.getId() == null) {
                entityManager.persist(employee);
            } else {
                employee = entityManager.merge(employee);
            }
            entityManager.getTransaction().commit(); //uncomment if not using @Transactional

            return Optional.of(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee != null ? Optional.of(employee) : Optional.empty();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        entityManager.getTransaction().begin(); //uncomment if not using @Transactional

        if (entityManager.contains(employee)) {
            entityManager.remove(employee);
        } else {
            entityManager.merge(employee);
        }

        entityManager.getTransaction().commit(); //uncomment if not using @Transactional
    }

}
