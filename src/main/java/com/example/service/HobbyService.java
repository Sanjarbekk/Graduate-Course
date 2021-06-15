package com.example.service;

import com.example.model.Hobby;
import com.example.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {

    @Autowired
    private HobbyRepository hobbyRepository;

    public Hobby save(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    public List<Hobby> getAll() {
        return hobbyRepository.findAll();
    }

    public Hobby getById(Long id) {
        return hobbyRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        hobbyRepository.deleteById(id);
    }

    public void update(Hobby hobby) {
        Hobby hobbyUpdate = hobbyRepository.findById(hobby.getId()).orElse(null);
        assert hobbyUpdate != null;
        hobbyUpdate.setName(hobby.getName());
        hobbyRepository.save(hobbyUpdate);
    }
}