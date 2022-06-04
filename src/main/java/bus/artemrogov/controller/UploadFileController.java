package bus.artemrogov.controller;

import bus.artemrogov.config.FileStorageProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping(value = "api/upload/v1")
public class UploadFileController {


    private final Path fileStorageLocation;

    @Autowired
    public UploadFileController(FileStorageProperty fileStorageProperties) throws Exception {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }


    @PostMapping(value = "/avatar")
    public String uploadAvatarImg(
            @RequestParam(value = "file") MultipartFile file
            ) throws IOException {


        String fileName = file.getOriginalFilename();

        assert fileName != null;
        if (fileName.isEmpty()){
            throw new IOException("file name not null");
        }

        Path targetLocation = fileStorageLocation.resolve(fileName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

}
