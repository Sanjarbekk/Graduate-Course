package com.example.service;

import com.example.model.Marks;
import com.example.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;

    public Marks save(Marks marks) {
        return marksRepository.save(marks);
    }

    public List<Marks> getAll() {
        return marksRepository.findAll();
    }

    public Marks getById(Long id) {
        return marksRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        marksRepository.deleteById(id);
    }

    public void update(Marks marks) {
        Marks marksUpdate = marksRepository.findById(marks.getId()).orElse(null);
        assert marksUpdate != null;
        marksUpdate.setCourse_1(marks.getCourse_1());
        marksUpdate.setCourse_2(marks.getCourse_2());
        marksUpdate.setCourse_3(marks.getCourse_3());
        marksUpdate.setCourse_4(marks.getCourse_4());
        marksRepository.save(marksUpdate);
    }
}
