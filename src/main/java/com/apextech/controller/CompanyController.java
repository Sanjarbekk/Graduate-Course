package com.apextech.controller;

import com.apextech.model.Company;
import com.apextech.service.CompanyService;
import com.apextech.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/company")
@Controller
public class CompanyController {

    @Autowired
    private final CompanyService companyService;
    private final StudentService studentService;

    public CompanyController(CompanyService companyService, StudentService studentService) {
        this.companyService = companyService;
        this.studentService = studentService;
    }

    @RequestMapping({"/", "/allCompany", "/listCompany", "/index", "/index.html", "list"})
    public String findAll(Model model) {
        List<Company> mainList = new ArrayList<>(companyService.getAll());
        mainList.sort(Comparator.comparing(Company::getId));
        model.addAttribute("companies", mainList);
        return "company/index";
    }

    @RequestMapping("/show/{id}")
    public String getById(@PathVariable String id, Model model) {
        model.addAttribute("company", companyService.getById(Long.valueOf(id)));
        return "company/show";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        if (checkForDelete(Long.valueOf(id))) {
            return "redirect:/company/list";
        }
        companyService.deleteById(Long.valueOf(id));
        return "redirect:/company/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("company", companyService.getById(Long.valueOf(id)));
        return "company/edit";
    }

    @RequestMapping(value = "/new")
    public String addNew(Model model) {
        model.addAttribute("company", new Company());
        return "company/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Company company, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/company/new";
        }
        Company savedProduct = companyService.save(company);
        return "redirect:/company/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Company company) {
        companyService.update(company);
        return "redirect:/company/list";
    }

    private boolean checkForDelete(Long id) {
        Set<Long> companies = studentService.ReturnCompanyId();
        return companies.contains(id);
    }
}
