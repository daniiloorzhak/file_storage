package ru.oorzhak.filestorage.service.impl;

import org.springframework.stereotype.Service;
import ru.oorzhak.filestorage.dto.FileDTO;
import ru.oorzhak.filestorage.service.FileService;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileDTO> findAllFiles() {
        return null;
    }

    @Override
    public List<FileDTO> findFilesInCatalog(Long catalogId) {
        return null;
    }

    @Override
    public FileDTO save(FileDTO fileDTO) {
        return null;
    }

    @Override
    public FileDTO save(String fileName, Long catalogueId) {
        return null;
    }

    @Override
    public FileDTO rename(Long id, String newName) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
