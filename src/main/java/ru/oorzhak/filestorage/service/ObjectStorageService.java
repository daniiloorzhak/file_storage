package ru.oorzhak.filestorage.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;
import java.io.InputStream;

public interface ObjectStorageService {

    PutObjectResult upload(Long fileId, File file);

    public S3Object download(String path, String fileName);
}
