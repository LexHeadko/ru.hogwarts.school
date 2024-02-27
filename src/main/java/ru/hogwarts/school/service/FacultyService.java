package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;

public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private Long lastId = 0L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findBook(Long id) {
        return faculties.get(id);
    }

    public Faculty editBook(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteBook(long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> getAllBooks() {
        return faculties.values();
    }
}