package com.murebackend.murebackend.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileDownloadUtil {
    private Path foundFile;

    public UrlResource getFileAsResource(String filecode) throws IOException {
        Path dirPath = Paths.get("files");

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(filecode)) {
                foundFile = file;
                return;
            }
        });
        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }
}
