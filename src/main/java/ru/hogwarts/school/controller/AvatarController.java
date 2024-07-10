package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.service.AvatarService;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadAvatar(@RequestParam long studentId, @RequestParam MultipartFile avatar) {
        avatarService.uploadAvatar(studentId, avatar);
    }

    @GetMapping("/from-db")
    public ResponseEntity<byte[]> getAvatarFrom(@RequestParam long studentId) {
    }
    @GetMapping("/from-fs")
    public ResponseEntity<byte[]> getAvatarFrom(@RequestParam long studentId) {
    }
}
