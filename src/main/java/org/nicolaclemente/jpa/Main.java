package org.nicolaclemente.jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.nicolaclemente.jpa.Entity.Company;
import org.nicolaclemente.jpa.Entity.Employee;
import org.nicolaclemente.jpa.Entity.Salary;
import org.nicolaclemente.jpa.Repository.EmployeeRepositoryImpl;

import java.util.Optional;

public class Main {
    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Non dimenticare di lanciare Mysql prima di eseguire questo codice");

        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

        //create a new Employee
        Employee employee = new Employee();
        employee.setfName("Mary");
        employee.setlName("Doe");
        employee.setYearsExperience(20);

        //set salary
        employee.setSalary(new Salary(5400.0, true));

        //set company
        employee.setCompany(new Company("MyCompany"));

        Employee employee2 = new Employee();
        employee2.setfName("Nicola");
        employee2.setlName("Clemente");
        employee2.setYearsExperience(5);

        //save Employees
        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        //update Employee
        Optional<Employee> retrievedEmployee = employeeRepository.getEmployeeById(Long.valueOf(1));
        retrievedEmployee.get().setlName("Valerio");
        employeeRepository.save(retrievedEmployee.get());

        //delete Employee
        employeeRepository.deleteEmployee(employee2);


        System.out.println("Close conn");
        entityManager.close();
        entityManagerFactory.close();
    }
}