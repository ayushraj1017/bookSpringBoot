package com.springboot.book.springbook.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
    // it will give path till resources
    public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();
    // public final String UPLOAD_DIR =
    // "C:\\Users\\asus\\Desktop\\SpringBoot\\springbook\\src\\main\\resources\\static";

    public FileUploadHelper() throws IOException {

    }

    public boolean uploadFile(MultipartFile multipartFile) {
        boolean bool = false;
        try {
            Files.copy(multipartFile.getInputStream(),
                    Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            bool = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;

    }

}
