package peaksoft.dao;

import peaksoft.model.Company;

import java.util.List;

public interface CompanyDao {
    void save(Company company);

    List<Company> getCompanies();

    Company getCompanyById(Long id);

    void deleteCompany(Long id);

    void updateCompany( Long id,Company updatedCompany);
}
