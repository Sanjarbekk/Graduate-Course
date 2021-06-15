package com.example.controller;

import com.example.model.*;
import com.example.model.modelDTO.MarksDTO;
import com.example.model.modelDTO.StudentDTO;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private final StudentService studentService;
    private final ProvinceService provinceService;
    private final SubjectService subjectService;
    private final FacultyService facultyService;
    private final MarksService marksService;
    private final CompanyService companyService;
    private final HobbyService hobbyService;

    public StudentController(StudentService studentService, ProvinceService provinceService, SubjectService subjectService, FacultyService facultyService, MarksService marksService, CompanyService companyService, HobbyService hobbyService) {
        this.studentService = studentService;
        this.provinceService = provinceService;
        this.subjectService = subjectService;
        this.facultyService = facultyService;
        this.marksService = marksService;
        this.companyService = companyService;
        this.hobbyService = hobbyService;
    }

    @RequestMapping({"/", "/allStudent", "/listStudent", "/index", "/index.html", "list"})
    public String findAll(Model model) {
        List<Student> mainList = new ArrayList<>(studentService.getAll());
        mainList.sort(Comparator.comparing(Student::getId));
        model.addAttribute("student", mainList);

        return "student/index";
    }

    @RequestMapping("/show/{id}")
    public String getById(@PathVariable String id, Model model) {
        model.addAttribute("student", studentService.getById(Long.valueOf(id)));
        return "student/show";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        studentService.deleteById(Long.valueOf(id));
        return "redirect:/student/list";
    }

    @RequestMapping(value = "/new")
    public String addNew(Model model) {
        model.addAttribute("studentDTO", new StudentDTO());
        return "student/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(StudentDTO studentDTO) {
        Student student = new Student();
        student = setStudentItems(studentDTO);
        studentService.save(student);
        return "redirect:/student/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Student student = studentService.getById(Long.valueOf(id));
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(Long.valueOf(id));
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        MarksDTO marksDTO = new MarksDTO();
        marksDTO.setCourse_1(student.getMarks().getCourse_1());
        marksDTO.setCourse_2(student.getMarks().getCourse_2());
        marksDTO.setCourse_3(student.getMarks().getCourse_3());
        marksDTO.setCourse_4(student.getMarks().getCourse_4());
        studentDTO.setMarksDTO(marksDTO);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        studentDTO.setBirthDate(LocalDate.parse(student.getDate(), formatters));
        model.addAttribute("studentDTO", studentDTO);
        return "student/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(StudentDTO studentDTO) {
        Student studentUpdate = new Student();
        studentUpdate = setStudentItems(studentDTO);
        studentUpdate.setId(studentDTO.getId());
        studentService.update(studentUpdate);
        return "redirect:/student/list";
    }

    @ModelAttribute("provinces")
    public List<Province> getProvinceList() {
        List<Province> provinces = new ArrayList<>();
        provinces = provinceService.getAll();
        return provinces;
    }

    @ModelAttribute("faculties")
    public List<Faculty> getFacultyList() {
        List<Faculty> faculties = new ArrayList<>();
        faculties = facultyService.getAll();
        return faculties;
    }

    @ModelAttribute("subjectsList")
    public List<Subject> getSubjectList() {
        return subjectService.getAll();
    }

    @ModelAttribute("companiesList")
    public List<Company> getCompanyList() {
        return companyService.getAll();
    }

    @ModelAttribute("hobbiesList")
    public List<Hobby> getHobbyList() {
        return hobbyService.getAll();
    }

    @ModelAttribute("marks")
    public List<Marks> getMarksList() {
        List<Marks> marks = new ArrayList<>();
        marks = marksService.getAll();
        return marks;
    }

    @ModelAttribute("genders")
    public List<String> getGenderList() {
        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        return genders;
    }

    /**
     * Methods
     */
    private Student setStudentItems(StudentDTO studentDTO) {
        Student student = new Student();
        Marks marks = new Marks();
        marks.setCourse_1(studentDTO.getMarksDTO().getCourse_1());
        marks.setCourse_2(studentDTO.getMarksDTO().getCourse_2());
        marks.setCourse_3(studentDTO.getMarksDTO().getCourse_3());
        marks.setCourse_4(studentDTO.getMarksDTO().getCourse_4());
        Marks marks1 = marksService.save(marks);

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setFaculty(facultyService.getById(studentDTO.getFacultyId()));
        student.setProvince(provinceService.getById(studentDTO.getProvinceId()));
        student.setGender(studentDTO.getGender());
        student.setDate(studentDTO.getBirthDate().toString());
        student.setMarks(marksService.getById(marks1.getId()));

        Set<Subject> subjects = new HashSet<>();
        for (Long aLong : studentDTO.getSubjectId()) {
            Subject subject = subjectService.getById(aLong);
            subjects.add(subject);
        }
        student.setSubjects(subjects);

        Set<Company> companies = new HashSet<>();
        for (Long aLong : studentDTO.getCompanyId()) {
            Company company = companyService.getById(aLong);
            companies.add(company);
        }
        student.setCompanies(companies);

        Set<Hobby> hobbies = new HashSet<>();
        for (Long aLong : studentDTO.getHobbyId()) {
            Hobby hobby = hobbyService.getById(aLong);
            hobbies.add(hobby);
        }
        student.setHobbies(hobbies);

        return student;
    }
}
