package ru.oorzhak.filestorage.service;

import ru.oorzhak.filestorage.dto.catalogue.CatalogueChildDTO;
import ru.oorzhak.filestorage.dto.catalogue.CatalogueResponseDTO;
import ru.oorzhak.filestorage.dto.catalogue.CatalogueRootDTO;

import java.util.List;

public interface CatalogueService {
    CatalogueResponseDTO findUserByUsername(String username);
    List<CatalogueResponseDTO> findAll();
    CatalogueResponseDTO findParent(Long childId);
    CatalogueResponseDTO findById(Long id);
    List<CatalogueResponseDTO> findChildren(Long parentId);
    CatalogueResponseDTO save(CatalogueChildDTO catalogueChildDTO, Long parentId);
    CatalogueResponseDTO save(CatalogueRootDTO catalogueRootDTO);
    CatalogueResponseDTO renameDirectory(Long id, String newName);
    void delete(Long id);
}
