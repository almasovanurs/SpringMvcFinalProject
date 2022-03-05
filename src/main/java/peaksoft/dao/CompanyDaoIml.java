package peaksoft.dao;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CompanyDaoIml implements CompanyDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Company company) {
        entityManager.persist(company);

    }

    @Override
    public List<Company> getCompanies() {
        return entityManager.createQuery("SELECT e FROM Company e", Company.class).getResultList();
    }

    @Override
    public Company getCompanyById(Long id) {
        return entityManager.find(Company.class, id);

    }

    @Override
    public void deleteCompany(Long id) {
        entityManager.remove(getCompanyById(id));
    }

    @Override
    public void updateCompany(Long id, Company updatedCompany) {
        Company company = getCompanyById(id);
        company.setCompanyName(updatedCompany.getCompanyName());
        company.setLocatedCountry(updatedCompany.getLocatedCountry());
        entityManager.merge(company);
    }
}
