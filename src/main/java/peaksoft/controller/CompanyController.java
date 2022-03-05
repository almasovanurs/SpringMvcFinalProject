package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.service.ServiceCompanyImpl;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final ServiceCompanyImpl serviceCompany;

    @Autowired
    public CompanyController(ServiceCompanyImpl serviceCompany) {
        this.serviceCompany = serviceCompany;
    }

    @GetMapping("/new")
    public String newCompany(Model model) {
        model.addAttribute("company", new Company());
        return "company/newCompany";
    }

    @PostMapping("/save")
    public String createCompany(@ModelAttribute("company") Company company) {
        serviceCompany.save(company);
        return "redirect:/companies";
    }

    @GetMapping
    public String getCompanies(Model model){
        model.addAttribute("companyLists",serviceCompany.getCompanies());
        return "company/getCompanies";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("company", serviceCompany.getCompanyById(id));
        return "company/editCompany";
    }

    @PatchMapping("/{id}")
    public String updateCompany(@ModelAttribute("company") Company company, @PathVariable("id") Long id){
        serviceCompany.updateCompany(id, company);
        return "redirect:/companies";
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable ("id") Long id){
        serviceCompany.deleteCompany(id);
        return "redirect:/companies";
    }

}
