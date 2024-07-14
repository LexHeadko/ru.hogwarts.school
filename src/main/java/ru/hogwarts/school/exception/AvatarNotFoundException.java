package ru.hogwarts.school.exception;

import ru.hogwarts.school.entity.Avatar;

public class AvatarNotFoundException extends NotFoundException {

    private final long studentId;

    public AvatarNotFoundException(long id){
        this.studentId = id;
    }

    @Override
    public String getMessage() {
        return "Аватар для сдудента с id = %d не найден!".formatted(studentId);
    }
}
