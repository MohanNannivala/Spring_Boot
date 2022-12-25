package com.example.spring_boot.controller;

import com.example.spring_boot.model.Student;
import com.example.spring_boot.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class studentController {


    private final StudentService studentService;

    @Autowired
    public studentController(StudentService studentService){
        this.studentService= studentService;
    }

    @GetMapping
    public List<Student>  getAllStudents(){
       return studentService.getAllStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping("{studentId}")
    public void updateStudent(@PathVariable Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }

}
