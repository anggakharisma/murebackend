package com.murebackend.murebackend.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    public static String saveFile(String fileName, MultipartFile multipartFile, String path)
            throws IOException {
        Path uploadPath = Paths.get("src/main/resources/public/" + path);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy_mm_dd_");

        String formattedDate = currentDateTime.format(dateFormat);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        String fileFullName = formattedDate + fileCode + "_" + fileName;
        try {
            Path filePath = uploadPath.resolve(formattedDate + fileCode + "_" + fileName);
            multipartFile.transferTo(filePath);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }

        return fileFullName;
    }
}
