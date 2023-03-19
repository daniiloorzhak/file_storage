package ru.oorzhak.filestorage.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;
import ru.oorzhak.filestorage.models.File;

import java.io.IOException;
import java.util.List;

public interface MetadataService {
    void uploadMultiPartFile(MultipartFile file) throws IOException;
    S3Object download(long id);
    List<File> list();
}
