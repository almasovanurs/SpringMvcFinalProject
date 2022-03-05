package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CompanyDao;
import peaksoft.model.Company;

import java.util.List;
@Service
public class ServiceCompanyImpl implements ServiceCompany {
    private CompanyDao companyDao;

    @Autowired
    public ServiceCompanyImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public void save(Company company) {
        companyDao.save(company);
    }

    @Override
    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyDao.getCompanyById(id);
    }

    @Override
    public void deleteCompany(Long id) {
        companyDao.deleteCompany(id);
    }

    @Override
    public void updateCompany(Long id,Company updatedCompany) {
        companyDao.updateCompany(id,updatedCompany);
    }
}
