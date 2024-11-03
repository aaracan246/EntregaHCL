package org.example.students

class Student(val name: String) {
    private val grades = mutableListOf<Double>()
    private val courses = mutableListOf<Course>()

    fun addCourse(course: Course, grade: Double) {
        courses.add(course)
        grades.add(grade)
        println("$name has been assigned to ${course.title} with a grade of $grade.")
    }


    fun averageGrade(): Double {
        return if (grades.isNotEmpty()) {
            grades.average()
        } else {
            0.0
        }
    }
}


data class Course(val title: String)
class Teacher(val name: String) {

    fun assignCourse(student: Student, course: Course, grade: Double) {
        student.addCourse(course, grade)
    }
}


fun main() {

    val student1 = Student("Tim")
    val student2 = Student("Bob")
    val math = Course("Mathematics")
    val science = Course("Science")
    val teacher = Teacher("Mr. Wrinkles")


    teacher.assignCourse(student1, math, 8.5)
    teacher.assignCourse(student1, science, 9.0)
    teacher.assignCourse(student2, math, 7.5)
    teacher.assignCourse(student2, science, 8.0)


    println("${student1.name}'s average grade: ${student1.averageGrade()}")
    println("${student2.name}'s average grade: ${student2.averageGrade()}")
}