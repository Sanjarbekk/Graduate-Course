package com.apextech.controller;

import com.apextech.model.Faculty;
import com.apextech.service.FacultyService;
import com.apextech.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/faculty")
@Controller
public class FacultyController {

    @Autowired
    private final FacultyService facultyService;
    private final StudentService studentService;

    public FacultyController(FacultyService facultyService, StudentService studentService) {
        this.facultyService = facultyService;
        this.studentService = studentService;
    }

    @RequestMapping({"/", "/allFaculty", "/listFaculty", "/index", "/index.html", "list"})
    public String findAll(Model model) {
        List<Faculty> mainList = new ArrayList<>(facultyService.getAll());
        mainList.sort(Comparator.comparing(Faculty::getId));
        model.addAttribute("faculties", mainList);
        return "faculty/index";
    }

    @RequestMapping("/show/{id}")
    public String getById(@PathVariable String id, Model model) {
        model.addAttribute("faculty", facultyService.getById(Long.valueOf(id)));
        return "faculty/show";
    }

    //Delete One Faculty with Id
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        if (checkForDelete(Long.valueOf(id))) {
            return "redirect:/faculty/list";
        }
        facultyService.deleteById(Long.valueOf(id));
        return "redirect:/faculty/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("faculty", facultyService.getById(Long.valueOf(id)));
        return "faculty/edit";
    }

    @RequestMapping(value = "/new")
    public String addNew(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "faculty/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Faculty faculty, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/faculty/new";

        }
        Faculty savedProduct = facultyService.save(faculty);
        return "redirect:/faculty/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Faculty faculty) {
        facultyService.update(faculty);
        return "redirect:/faculty/list";
    }

    private boolean checkForDelete(Long id) {
        Set<Long> faculties = studentService.ReturnFacultyId();
        return faculties.contains(id);
    }
}

