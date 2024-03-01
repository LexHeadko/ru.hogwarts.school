package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.hogwarts.school.model.Faculty;

public class FacultyNotFoundException extends NotFoundException{
    private final long id;
    public FacultyNotFoundException(long id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Факультет с id = %d не найден!".formatted(id);
    }
}
