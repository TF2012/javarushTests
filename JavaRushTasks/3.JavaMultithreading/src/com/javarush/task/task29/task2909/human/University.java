package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University extends UniversityPerson {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public  void setStudents(List<Student> students) {
        this.students = students;
    }

    public  List<Student> getStudents() {
        return students;
    }

    public University(List<Student> students, String name, int age) {
        super(name, age);
        this.students = students;
    }

    public  int getAge() {
        return age;
    }

    public  void setAge(int age) {
        this.age = age;
    }

    public  String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public University(String name, int age) {
        super(name, age);
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student s = students.get(0);
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).getAverageGrade() == averageGrade) {
                s = students.get(i);
            }
        }
        return s;
    }

    public Student getStudentWithMaxAverageGrade() {
        double d = students.get(0).getAverageGrade();
        Student s = students.get(0);
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).getAverageGrade() > d) {
                d = students.get(i).getAverageGrade();
                s = students.get(i);
            }
        }
        return s;
    }

    public Student getStudentWithMinAverageGrade() {
        double d = students.get(0).getAverageGrade();
        Student s = students.get(0);
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).getAverageGrade() < d) {
                d = students.get(i).getAverageGrade();
                s = students.get(i);
            }
        }
        return s;
    }

    public void expel(Student student) {
        for (int i = 0; i < students.size(); i++){
            if (students.get(i) == student) students.remove(i);
        }
    }
}