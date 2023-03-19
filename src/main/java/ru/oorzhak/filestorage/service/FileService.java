package ru.oorzhak.filestorage.service;

import ru.oorzhak.filestorage.dto.FileDTO;

import java.util.List;

public interface FileService {
    List<FileDTO> findAllFiles();
    List<FileDTO> findFilesInCatalog(Long catalogId);
    FileDTO save(FileDTO fileDTO);
    FileDTO save(String fileName, Long catalogueId);
    FileDTO rename(Long id, String newName);
    void delete(Long id);
}
