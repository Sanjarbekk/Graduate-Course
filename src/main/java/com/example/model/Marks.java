package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "marks")

public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "course_1")
    private int course_1;

    @Column(name = "course_2")
    private int course_2;

    @Column(name = "course_3")
    private int course_3;


    @Column(name = "course_4")
    private int course_4;


    @OneToOne(mappedBy = "marks")
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCourse_1() {
        return course_1;
    }

    public void setCourse_1(int course_1) {
        this.course_1 = course_1;
    }

    public int getCourse_2() {
        return course_2;
    }

    public void setCourse_2(int course_2) {
        this.course_2 = course_2;
    }

    public int getCourse_3() {
        return course_3;
    }

    public void setCourse_3(int course_3) {
        this.course_3 = course_3;
    }

    public int getCourse_4() {
        return course_4;
    }

    public void setCourse_4(int course_4) {
        this.course_4 = course_4;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

