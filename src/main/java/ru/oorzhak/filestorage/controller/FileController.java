package ru.oorzhak.filestorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.oorzhak.filestorage.dto.FileDTO;
import ru.oorzhak.filestorage.service.FileService;
import ru.oorzhak.filestorage.service.ObjectStorageService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "files")
public class FileController {

    private final FileService fileService;
    private final ObjectStorageService objectStorageService;

    @Autowired
    public FileController(FileService fileService, ObjectStorageService objectStorageService) {
        this.fileService = fileService;
        this.objectStorageService = objectStorageService;
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
        }
        return file;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileDTO> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam Long catalogId) {
        FileDTO fileDTO = fileService.save(multipartFile.getName(), catalogId);
        System.out.println(multipartFile.getOriginalFilename());
        try {
            objectStorageService.upload(4352L, convertMultiPartFileToFile(multipartFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(fileDTO);
    }
}
