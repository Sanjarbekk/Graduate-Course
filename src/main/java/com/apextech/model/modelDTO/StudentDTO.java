package com.apextech.model.modelDTO;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.Set;

public class StudentDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private Long facultyId;

    private Long provinceId;

    private Set<Long> subjectId;

    private MarksDTO marksDTO;

    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public MarksDTO getMarksDTO() {
        return marksDTO;
    }

    public void setMarksDTO(MarksDTO marksDTO) {
        this.marksDTO = marksDTO;
    }

    private Set<Long> companyId;

    private Set<Long> hobbyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Set<Long> getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Set<Long> subjectId) {
        this.subjectId = subjectId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Long> getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Set<Long> companyId) {
        this.companyId = companyId;
    }

    public Set<Long> getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Set<Long> hobbyId) {
        this.hobbyId = hobbyId;
    }
}
