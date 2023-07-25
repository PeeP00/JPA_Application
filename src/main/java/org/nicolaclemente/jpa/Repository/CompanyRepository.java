package org.nicolaclemente.jpa.Repository;

import org.nicolaclemente.jpa.Entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {
    Optional<Company> save(Company company);
    Optional<Company> getCompanyById(Long id);
    void deleteCompany(Company company);
}
