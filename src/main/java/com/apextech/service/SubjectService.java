package com.apextech.service;

import com.apextech.model.Subject;
import com.apextech.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Subject getById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    public void update(Subject subject) {
        Subject subjectUpdate = subjectRepository.findById(subject.getId()).orElse(null);
        assert subjectUpdate != null;
        subjectUpdate.setName(subject.getName());
        subjectRepository.save(subjectUpdate);
    }
}
