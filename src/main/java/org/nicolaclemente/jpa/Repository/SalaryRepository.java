package org.nicolaclemente.jpa.Repository;

import org.nicolaclemente.jpa.Entity.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository {
    Optional<Salary> save(Salary salary);
    Optional<Salary> getSalaryById(Long id);
    void deleteSalary(Salary salary);
}
