package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.lang.model.util.SimpleElementVisitor14;
import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student) {
        student.setId(null);
        fillFaculty(student.getFaculty(), student);
        return studentRepository.save(student);
    }

    public Student editStudent(long id, Student student) {
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setName(student.getName());
                    oldStudent.setAge(student.getAge());
                    fillFaculty(student.getFaculty(), oldStudent);
                    return studentRepository.save(oldStudent);
                })
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student deleteStudent(long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    return student;
                })
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Faculty findFaculty(long id) {
        return findStudent(id).getFaculty();
    }

    private void fillFaculty(Faculty faculty, Student student) {
        if (faculty != null && faculty.getId() != null) {
            Faculty facultyFromDb = facultyRepository.findById(faculty.getId())
                    .orElseThrow(() -> new FacultyNotFoundException(faculty.getId()));
            student.setFaculty(facultyFromDb);
        }
    }
}
