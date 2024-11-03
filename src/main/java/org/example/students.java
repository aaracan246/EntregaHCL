package org.example;


import java.util.ArrayList;
import java.util.List;

class Student {
    private final String name;
    private final List<Double> grades;
    private final List<Course> courses;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course, double grade) {
        courses.add(course);
        grades.add(grade);
        System.out.println(name + " has been assigned to " + course.getTitle() + " with a grade of " + grade + ".");
    }

    public double averageGrade() {
        if (!grades.isEmpty()) {
            return grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        } else {
            return 0.0;
        }
    }

    public String getName() {
        return name;
    }
}

class Course {
    private final String title;

    public Course(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class Teacher {
    private final String name;

    public Teacher(String name) {
        this.name = name;
    }

    public void assignCourse(Student student, Course course, double grade) {
        student.addCourse(course, grade);
    }
}

class StudentManagement {
    public static void main(String[] args) {
        Student student1 = new Student("Tim");
        Student student2 = new Student("Bob");
        Course math = new Course("Mathematics");
        Course science = new Course("Science");
        Teacher teacher = new Teacher("Mr. Wrinkles");

        teacher.assignCourse(student1, math, 8.5);
        teacher.assignCourse(student1, science, 9.0);
        teacher.assignCourse(student2, math, 7.5);
        teacher.assignCourse(student2, science, 8.0);

        System.out.println(student1.getName() + "'s average grade: " + student1.averageGrade());
        System.out.println(student2.getName() + "'s average grade: " + student2.averageGrade());
    }
}