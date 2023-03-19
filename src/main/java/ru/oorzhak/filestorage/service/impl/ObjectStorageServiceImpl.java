package ru.oorzhak.filestorage.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oorzhak.filestorage.repository.FileRepository;
import ru.oorzhak.filestorage.service.ObjectStorageService;

import java.io.File;

@Service
public class ObjectStorageServiceImpl implements ObjectStorageService {

    private final AmazonS3 amazonS3;
    private final FileRepository fileRepository;

    @Autowired
    public ObjectStorageServiceImpl(AmazonS3 amazonS3, FileRepository fileRepository) {
        this.amazonS3 = amazonS3;
        this.fileRepository = fileRepository;
    }

    @Override
    public PutObjectResult upload(Long fileId, File file) {
        final PutObjectRequest putObjectRequest = new PutObjectRequest("cft-project", "dsg/sgoidg/sdgshs", file);
        return amazonS3.putObject(putObjectRequest);
    }

    @Override
    public S3Object download(String path, String fileName) {
        return null;
    }
}
