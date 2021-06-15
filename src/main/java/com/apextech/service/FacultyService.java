package com.apextech.service;

import com.apextech.model.Faculty;
import com.apextech.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Faculty getById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        facultyRepository.deleteById(id);
    }

    public void update(Faculty faculty) {
        if (faculty.getId() != null) {
            Faculty facultyUpdate = facultyRepository.findById(faculty.getId()).orElse(null);
            assert facultyUpdate != null;
            facultyUpdate.setName(faculty.getName());
            facultyRepository.save(facultyUpdate);
        }

    }
}
