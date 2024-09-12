package com.example.escrudtest.services;

import com.example.escrudtest.entities.Student;
import com.example.escrudtest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student create(Student student) {
        Student createdStudent = studentRepository.save(student);
        return createdStudent;
    }

    public List<Student> readAll() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents;
    }

    public Optional<Student> readById(Long id) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        return foundStudent;
    }

    public Optional<Student> update(Long id, Student student) {
        Optional<Student> updateStudent = studentRepository.findById(id);
        if (updateStudent.isEmpty()) {
            return Optional.empty();

        } else {
            updateStudent.get().setName(student.getName());
            updateStudent.get().setSurname(student.getSurname());
            updateStudent.get().setWorking(student.isWorking());

            Student responseStudent = studentRepository.save(updateStudent.get());
            return Optional.of(responseStudent);
        }
    }

    public Optional<Student> updateWorking(Long id, boolean isWorking) {
        Optional<Student> updateStudent = studentRepository.findById(id);
        if (updateStudent.isEmpty()) {
            return Optional.empty();

        } else {
            updateStudent.get().setWorking(isWorking);

            Student responseStudent = studentRepository.save(updateStudent.get());
            return Optional.of(responseStudent);
        }
    }

    public boolean delete(Long id) {
        boolean isThere = studentRepository.existsById(id);
        if (isThere) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
