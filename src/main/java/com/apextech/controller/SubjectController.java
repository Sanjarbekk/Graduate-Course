package com.apextech.controller;

import com.apextech.model.Subject;
import com.apextech.service.StudentService;
import com.apextech.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/subject")
@Controller
public class SubjectController {
    @Autowired
    private final SubjectService subjectService;
    private final StudentService studentService;

    public SubjectController(SubjectService subjectService, StudentService studentService) {
        this.subjectService = subjectService;
        this.studentService = studentService;
    }

    @RequestMapping({"/", "/allSubject", "/listSubject", "/index", "/index.html", "list"})
    public String findAll(Model model) {
        List<Subject> mainList = new ArrayList<>(subjectService.getAll());
        mainList.sort(Comparator.comparing(Subject::getId));
        model.addAttribute("subject", mainList);
        return "subject/index";
    }

    @RequestMapping("/show/{id}")
    public String getById(@PathVariable String id, Model model) {
        model.addAttribute("subject", subjectService.getById(Long.valueOf(id)));
        return "subject/show";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        if (checkForDelete(Long.valueOf(id))) {
            return "redirect:/subject/list";
        }
        subjectService.deleteById(Long.valueOf(id));
        return "redirect:/subject/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("subject", subjectService.getById(Long.valueOf(id)));
        return "subject/edit";
    }

    @RequestMapping(value = "/new")
    public String addSubject(Model model) {
        model.addAttribute("subject", new Subject());
        return "subject/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Subject subject, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/subject/new";
        }
        Subject savedProduct = subjectService.save(subject);
        return "redirect:/subject/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Subject subject) {
        subjectService.update(subject);
        return "redirect:/subject/list";
    }

    private boolean checkForDelete(Long id) {
        Set<Long> subjects = studentService.ReturnSubjectId();
        return subjects.contains(id);
    }
}
