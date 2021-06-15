package com.example.controller;

import com.example.model.Hobby;
import com.example.service.HobbyService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/hobby")
@Controller
public class HobbyController {

    @Autowired
    private final HobbyService hobbyService;
    private final StudentService studentService;

    public HobbyController(HobbyService hobbyService, StudentService studentService) {
        this.hobbyService = hobbyService;
        this.studentService = studentService;
    }

    @RequestMapping({"/", "/allHobby", "/listHobby", "/index", "/index.html", "list"})
    public String findAll(Model model) {
        List<Hobby> mainList = new ArrayList<>(hobbyService.getAll());
        mainList.sort(Comparator.comparing(Hobby::getId));
        model.addAttribute("hobbies", mainList);
        return "hobby/index";
    }

    @RequestMapping("/show/{id}")
    public String getById(@PathVariable String id, Model model) {
        model.addAttribute("hobby", hobbyService.getById(Long.valueOf(id)));
        return "hobby/show";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        if (checkForDelete(Long.valueOf(id))) {
            return "redirect:/hobby/list";
        }
        hobbyService.deleteById(Long.valueOf(id));
        return "redirect:/hobby/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("hobby", hobbyService.getById(Long.valueOf(id)));
        return "hobby/edit";
    }

    @RequestMapping(value = "/new")
    public String addNew(Model model) {
        model.addAttribute("hobby", new Hobby());
        return "hobby/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Hobby hobby, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/hobby/new";

        }
        Hobby savedProduct = hobbyService.save(hobby);
        return "redirect:/hobby/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Hobby hobby) {
        hobbyService.update(hobby);
        return "redirect:/hobby/list";
    }

    private boolean checkForDelete(Long id) {
        Set<Long> hobbies = studentService.ReturnHobbyId();
        return hobbies.contains(id);
    }
}