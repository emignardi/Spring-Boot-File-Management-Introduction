package com.mignardi.LearnFileManager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

    public static final String DIRECTORY = "C:\\Users\\migna\\Downloads\\Storage";

    public void saveFile(MultipartFile file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        var targetFile = new File(DIRECTORY + File.separator + file.getOriginalFilename());
        if (!Objects.equals(targetFile.getParent(), DIRECTORY)) {
            throw new SecurityException("Unsupported file name");
        }
        Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

    public File getDownloadFile(String file) throws Exception {
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        var targetFile = new File(DIRECTORY + File.separator + file);
        if (!Objects.equals(targetFile.getParent(), DIRECTORY)) {
            throw new SecurityException("Unsupported file name");
        }
        if (!targetFile.exists()) {
            throw new FileNotFoundException("No file named: " + file);
        }
        return targetFile;
    }
}
