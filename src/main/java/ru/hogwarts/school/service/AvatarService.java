package ru.hogwarts.school.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.AvatarProcessingException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final Path pathToAvatarsDir;


    public AvatarService(AvatarRepository avatarRepository,
                         StudentRepository studentRepository,
                         @Value("${application.path-to-avatars-dir}") String pathToAvatarsDir, String pathToAvatarsDir1) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
        this.pathToAvatarsDir = Paths.get(pathToAvatarsDir);
    }

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(pathToAvatarsDir) || !Files.isDirectory(pathToAvatarsDir)) {
                Files.createDirectories(pathToAvatarsDir);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void upLoadAvatar(long studentId, MultipartFile image) {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new StudentNotFoundException(studentId));
            byte[] data = image.getBytes();
            String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
            String fileName = String.format("%s.%s", UUID.randomUUID(), extension);
            Path path = pathToAvatarsDir.resolve(fileName);
            Files.write(path, data);

            Avatar avatar = new Avatar();
            avatar.setStudent(student);
            avatar.setData(data);
            avatar.getFileSize(image.getSize());
            avatar.setMediaType(image.getContentType());
            avatar.setFilePath(path.toString());

            avatarRepository.save(avatar);
        } catch (IOException e) {
            throw new AvatarProcessingException(e);
        }

    }
}
