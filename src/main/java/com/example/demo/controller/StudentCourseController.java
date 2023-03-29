package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {

    private StudentRepository studentRepository;

    private CourseRepository courseRepository;

    @Autowired
    public StudentCourseController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping()
    public Student saveStudentWithCourse(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping()
    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public Student findStudent(@PathVariable Long studentId){
        return studentRepository.findById(studentId).orElse(null);
    }


    @PostMapping("/update")
    public Student updateStudent(@RequestBody Student student){
        Student updateStudent = studentRepository.findById(student.getId()).orElse(null);
        updateStudent.setCourses(student.getCourses());
        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        updateStudent.setDept(student.getDept());
        return studentRepository.save(updateStudent);
    }

    @DeleteMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable Long studentId){
       studentRepository.deleteById(studentId);
       return "Id:"+studentId+" Student successfully deleted.";
    }


}
