package bus.artemrogov.controller;

import bus.artemrogov.config.FileStorageProperty;
import bus.artemrogov.response.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
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
    public UploadFileResponse uploadAvatarImg(@RequestParam(value = "file") MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();

        assert fileName != null;

        if (fileName.isEmpty()){
            throw new IOException("file name not null");
        }

        Path targetLocation = fileStorageLocation.resolve(fileName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        String fileDownloadPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/upload/v1/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(
                file.getOriginalFilename(),
                fileDownloadPath,
                file.getSize(),
                file.getContentType()
        );

    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {

            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
