package com.apextech.repository;

import com.apextech.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT faculty_id FROM student", nativeQuery = true)
    Set<Long> ReturnFacultyId();

    @Query(value = "SELECT province_id FROM student", nativeQuery = true)
    Set<Long> ReturnProvinceId();

    @Query(value = "SELECT company_id FROM student_companies", nativeQuery = true)
    Set<Long> ReturnCompanyId();

    @Query(value = "SELECT subject_id FROM student_subjects", nativeQuery = true)
    Set<Long> ReturnSubjectId();

    @Query(value = "SELECT hobby_id FROM student_hobbies", nativeQuery = true)
    Set<Long> ReturnHobbyId();

}
