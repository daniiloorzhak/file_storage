package ru.oorzhak.filestorage.service.impl;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.oorzhak.filestorage.models.File;
import ru.oorzhak.filestorage.repository.FileRepository;
import ru.oorzhak.filestorage.service.MetadataService;
import ru.oorzhak.filestorage.service.ObjectStorageService;

import java.io.IOException;
import java.util.List;

@Service
public class MetadataServiceImpl implements MetadataService {

    final private ObjectStorageService objectStorageService;

    final private FileRepository fileRepository;

    @Value("${yandex.bucket.name}")
    final private String bucketName;

    @Autowired
    public MetadataServiceImpl(ObjectStorageService objectStorageService, FileRepository fileRepository, String bucketName) {
        this.objectStorageService = objectStorageService;
        this.fileRepository = fileRepository;
        this.bucketName = bucketName;
    }

    @Override
    public void uploadMultiPartFile(MultipartFile file) throws IOException {
    }

    @Override
    public S3Object download(long id) {
        return null;
    }

    @Override
    public List<File> list() {
        return null;
    }
}
