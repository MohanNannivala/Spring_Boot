package com.example.spring_boot.service;

import com.example.spring_boot.model.Student;
import com.example.spring_boot.repository.StudentRepository;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository =studentRepository;
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {

        boolean exists = studentRepository.existsById(id);

        if(exists){
            studentRepository.deleteById(id);
        }else{
            throw new IllegalStateException("student with id "+id+" does not exits");
        }

    }

    @Transient
    public void updateStudent(Long id, String name, String email) {

       Student student = studentRepository.findById(id).orElse(null);

        if(student==null){
            throw new IllegalStateException("student with id "+id+" does not exits");
        }

        if(name!=null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email!=null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            student.setEmail(email);
        }
    }
}
