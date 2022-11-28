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
            // convert file to base 64 idk now tho
//            File fi = new File(foundFile.toUri());
//            byte[] fileContent = Files.readAllBytes(fi.toPath());

//            return new String(Base64.getEncoder().encodeToString(fileContent).getBytes(), StandardCharsets.UTF_8);

//            return Base64.getEncoder().encodeToString(fileContent);

            return new UrlResource(foundFile.toUri());
        }

        return null;
    }
}
