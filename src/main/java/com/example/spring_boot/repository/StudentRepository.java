package com.example.spring_boot.repository;

import com.example.spring_boot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //SELECT * FROM student WHERE email=?
    //@Query("SELECT s FROM Student s WHERE s.email = ?1") --JBQL
    Optional<Student> findStudentByEmail(String email);
}
