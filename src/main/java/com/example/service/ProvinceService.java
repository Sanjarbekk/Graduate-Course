package com.example.service;

import com.example.model.Province;
import com.example.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    public Province save(Province province) {
        return provinceRepository.save(province);
    }

    public List<Province> getAll() {
        return provinceRepository.findAll();
    }

    public Province getById(Long id) {
        return provinceRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        provinceRepository.deleteById(id);
    }

    public void update(Province province) {
        Province provinceUpdate = provinceRepository.findById(province.getId()).orElse(null);
        assert provinceUpdate != null;
        provinceUpdate.setName(province.getName());
        provinceRepository.save(provinceUpdate);
    }
}
