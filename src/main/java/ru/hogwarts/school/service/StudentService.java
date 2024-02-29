package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student editStudent(long id, Student student) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new StudentNotFoundException(id);
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new FacultyNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    public Student findStudent(Long id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new StudentNotFoundException(id);
        }
        return studentRepository.getById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public List<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }
}
