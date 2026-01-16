package com.example.inventory_backend.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path uploadRoot;
    private static final Set<String> ALLOWED = Set.of("image/png", "image/jpeg", "image/webp");

    public FileStorageService(@Value("${app.upload-dir:uploads}") String uploadDir) {
        this.uploadRoot = Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    public String storeImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty.");
        }
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED.contains(contentType)) {
            throw new IllegalArgumentException("Only PNG, JPG, or WEBP images are allowed.");
        }
        // 3MB max (adjust if needed)
        if (file.getSize() > 3 * 1024 * 1024) {
            throw new IllegalArgumentException("Max file size is 3MB.");
        }

        Files.createDirectories(uploadRoot);

        String original = StringUtils.cleanPath(file.getOriginalFilename() == null ? "image" : file.getOriginalFilename());
        String ext = "";
        int dot = original.lastIndexOf(".");
        if (dot >= 0) ext = original.substring(dot);

        String filename = UUID.randomUUID() + ext;
        Path target = uploadRoot.resolve(filename);

        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        // This is what frontend will use
        return "/uploads/" + filename;
    }
}
