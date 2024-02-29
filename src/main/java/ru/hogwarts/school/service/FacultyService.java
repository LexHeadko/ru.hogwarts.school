package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty editFaculty(long id, Faculty faculty) {
        if (facultyRepository.findById(id).isEmpty()) {
            throw new FacultyNotFoundException(id);
        }
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        if (facultyRepository.findById(id).isEmpty()) {
            throw new FacultyNotFoundException(id);
        }
        facultyRepository.deleteById(id);
    }

    public Faculty findFaculty(long id) {
        if (facultyRepository.getById(id) == null) {
            throw new FacultyNotFoundException(id);
        }
        return facultyRepository.getById(id);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public List<Faculty> findColor(String color) {
        return facultyRepository.findByColor(color);
    }
}