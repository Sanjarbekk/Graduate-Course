package com.apextech.model.modelDTO;

public class MarksDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public MarksDTO(Long id, int course_1, int course_2, int course_3, int course_4) {
        this.id = id;
        this.course_1 = course_1;
        this.course_2 = course_2;
        this.course_3 = course_3;
        this.course_4 = course_4;
    }

    public MarksDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    private int course_1;
    private int course_2;
    private int course_3;
    private int course_4;

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
}
