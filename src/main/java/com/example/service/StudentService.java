package com.example.service;

import com.example.model.Student;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public void update(Student student) {
        if (student.getId() != null) {
            Student studentUpdate = studentRepository.findById(student.getId()).orElse(null);
            studentUpdate.setFirstName(student.getFirstName());
            studentUpdate.setLastName(student.getLastName());
            studentUpdate.setFaculty(student.getFaculty());

            studentUpdate.setProvince(student.getProvince());
            studentUpdate.setSubjects(student.getSubjects());
            studentUpdate.setMarks(student.getMarks());
            studentUpdate.setGender(student.getGender());
            studentUpdate.setDate(student.getDate());
            studentUpdate.setCompanies(student.getCompanies());
            studentUpdate.setHobbies(student.getHobbies());
            studentRepository.save(studentUpdate);
        }
    }

    public Set<Long> ReturnCompanyId() {
        return studentRepository.ReturnCompanyId();
    }

    public Set<Long> ReturnFacultyId() {
        return studentRepository.ReturnFacultyId();
    }

    public Set<Long> ReturnHobbyId() {
        return studentRepository.ReturnHobbyId();
    }

    public Set<Long> ReturnProvinceId() {
        return studentRepository.ReturnProvinceId();
    }

    public Set<Long> ReturnSubjectId() {
        return studentRepository.ReturnSubjectId();
    }
}
