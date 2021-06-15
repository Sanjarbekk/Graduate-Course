package com.example.controller;

import com.example.model.Province;
import com.example.service.ProvinceService;
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

@RequestMapping("/province")
@Controller
public class ProvinceController {
    @Autowired
    private final ProvinceService provinceService;
    private final StudentService studentService;

    public ProvinceController(ProvinceService provinceService, StudentService studentService) {
        this.provinceService = provinceService;
        this.studentService = studentService;
    }

    @RequestMapping({"/", "/allProvince", "/listProvince", "/index", "/index.html", "list"})
    public String findAll(Model model) {
        List<Province> mainList = new ArrayList<>(provinceService.getAll());
        mainList.sort(Comparator.comparing(Province::getId));
        model.addAttribute("provinces", mainList);
        return "province/index";
    }

    @RequestMapping("/show/{id}")
    public String getById(@PathVariable String id, Model model) {
        model.addAttribute("province", provinceService.getById(Long.valueOf(id)));
        return "province/show";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        if (checkForDelete(Long.valueOf(id))) {
            return "redirect:/province/list";
        }
        provinceService.deleteById(Long.valueOf(id));
        return "redirect:/province/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("province", provinceService.getById(Long.valueOf(id)));
        return "province/edit";
    }

    @RequestMapping(value = "/new")
    public String addProvince(Model model) {
        model.addAttribute("province", new Province());
        return "province/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Province province, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/province/new";
        }
        Province savedProduct = provinceService.save(province);
        return "redirect:/province/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Province province) {
        provinceService.update(province);
        return "redirect:/province/list";
    }

    private boolean checkForDelete(Long id) {
        Set<Long> provinces = studentService.ReturnProvinceId();
        return provinces.contains(id);
    }
}
