package ru.oorzhak.filestorage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oorzhak.filestorage.dto.catalogue.CatalogueChildDTO;
import ru.oorzhak.filestorage.dto.catalogue.CatalogueResponseDTO;
import ru.oorzhak.filestorage.dto.catalogue.CatalogueRootDTO;
import ru.oorzhak.filestorage.exception.CatalogueNotFoundException;
import ru.oorzhak.filestorage.exception.ChildCatalogueAlreadyExists;
import ru.oorzhak.filestorage.models.Catalogue;
import ru.oorzhak.filestorage.repository.CatalogueRepository;
import ru.oorzhak.filestorage.repository.UserRepository;
import ru.oorzhak.filestorage.service.CatalogueService;

import java.util.List;

@Service
public class CatalogueServiceImpl implements CatalogueService {
    private static final Logger log = LoggerFactory.getLogger(CatalogueServiceImpl.class);

    private final CatalogueRepository catalogueRepository;

    private final UserRepository userRepository;
    @Autowired
    public CatalogueServiceImpl(CatalogueRepository catalogueRepository, UserRepository userRepository) {
        this.catalogueRepository = catalogueRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CatalogueResponseDTO findUserByUsername(String username) {
        return null;
    }

    @Override
    public List<CatalogueResponseDTO> findAll() {

        return catalogueRepository.findAll().stream().map( catalogue -> {
            CatalogueResponseDTO catalogueDTO = new CatalogueResponseDTO();

            catalogueDTO.setId(catalogue.getId());
            catalogueDTO.setName(catalogue.getName());
            catalogueDTO.setCreatorUsername(catalogue.getCreator().getUsername());
            catalogueDTO.setIsRoot(catalogue.getIsRoot());
            catalogueDTO.setIsPrivate(catalogue.getIsPrivate());

            return catalogueDTO;
        }).toList();
    }

    @Override
    public CatalogueResponseDTO findById(Long id) {
        Catalogue catalogue = catalogueRepository.findById(id).orElseThrow();

        CatalogueResponseDTO catalogueDTO = new CatalogueResponseDTO();
        catalogueDTO.setId(catalogue.getId());
        catalogueDTO.setName(catalogue.getName());
        catalogueDTO.setCreatorUsername(catalogue.getCreator().getUsername());
        catalogueDTO.setIsRoot(catalogue.getIsRoot());
        catalogueDTO.setIsPrivate(catalogue.getIsPrivate());

        return catalogueDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CatalogueResponseDTO> findChildren(Long parentId) {
        Catalogue parent = catalogueRepository.findById(parentId).orElseThrow();
        System.out.println("catalogue.getId()");

        List<Catalogue> catalogues = parent.getChildren();
        System.out.println(catalogues.size());
        return parent.getChildren().stream().map( catalogue -> {
            CatalogueResponseDTO catalogueDTO = new CatalogueResponseDTO();
            System.out.println(catalogue.getId());
            catalogueDTO.setId(catalogue.getId());
            catalogueDTO.setName(catalogue.getName());
            catalogueDTO.setCreatorUsername(catalogue.getCreator().getUsername());
            catalogueDTO.setIsRoot(catalogue.getIsRoot());
            catalogueDTO.setIsPrivate(catalogue.getIsPrivate());

            return catalogueDTO;
        }).toList();
    }

    @Override
    @Transactional
    public CatalogueResponseDTO save(CatalogueChildDTO catalogueChildDTO, Long parentId) {
        Catalogue parentCatalogue = catalogueRepository.findById(parentId).orElseThrow();

        var match = parentCatalogue.getChildren().stream().anyMatch(e -> e.getName().equals(catalogueChildDTO.getName()));

        if (match) throw new ChildCatalogueAlreadyExists();

        Catalogue childCatalogue = new Catalogue();

        childCatalogue.setName(catalogueChildDTO.getName());
        childCatalogue.setCreator(userRepository.findByUsername("admin"));
        childCatalogue.setIsRoot(false);
        childCatalogue.setIsPrivate(parentCatalogue.getIsPrivate());
        childCatalogue.setParent(parentCatalogue);
        childCatalogue.setChildren(null);
        childCatalogue.setFiles(null);

        childCatalogue = catalogueRepository.save(childCatalogue);
//        parentCatalogue = catalogueRepository.save(parentCatalogue);

        CatalogueResponseDTO catalogueDTO = new CatalogueResponseDTO();
        catalogueDTO.setId(childCatalogue.getId());
        catalogueDTO.setName(childCatalogue.getName());
        catalogueDTO.setCreatorUsername("admin");
        catalogueDTO.setIsRoot(childCatalogue.getIsRoot());
        catalogueDTO.setIsPrivate(childCatalogue.getIsPrivate());

        return catalogueDTO;
    }

    @Override
    @Transactional
    public CatalogueResponseDTO save(CatalogueRootDTO catalogueRootDTO) {
        Catalogue catalogue = new Catalogue();
        catalogue.setName(catalogueRootDTO.getName());
        catalogue.setCreator(userRepository.findByUsername("admin"));
        catalogue.setIsRoot(true);
        catalogue.setIsPrivate(catalogueRootDTO.getIsPrivate());
        catalogue.setParent(null);
        catalogue.setChildren(null);
        catalogue.setFiles(null);

        catalogue = catalogueRepository.save(catalogue);

        CatalogueResponseDTO catalogueDTO = new CatalogueResponseDTO();
        catalogueDTO.setName(catalogue.getName());
        catalogueDTO.setCreatorUsername("admin");
        catalogueDTO.setIsRoot(true);
        catalogueDTO.setIsPrivate(catalogue.getIsPrivate());

        return catalogueDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public CatalogueResponseDTO findParent(Long childId) {
        Catalogue child = catalogueRepository.findById(childId).orElseThrow();

        Catalogue parent = child.getParent();

        if (parent == null) throw new RuntimeException();

        CatalogueResponseDTO res = new CatalogueResponseDTO();
        res.setId(parent.getId());
        res.setName(parent.getName());
        res.setCreatorUsername(parent.getCreator().getUsername());
        res.setIsRoot(parent.getIsRoot());
        res.setIsPrivate(parent.getIsPrivate());

        return res;
    }

    @Override
    @Transactional
    public CatalogueResponseDTO renameDirectory(Long id, String newName) {
        Catalogue catalogue = catalogueRepository.findById(id).orElseThrow();

        catalogue.setName(newName);

        catalogueRepository.save(catalogue);

        CatalogueResponseDTO catalogueDTO = new CatalogueResponseDTO();
        catalogueDTO.setId(catalogue.getId());
        catalogueDTO.setName(newName);
        catalogueDTO.setCreatorUsername(catalogue.getCreator().getUsername());
        catalogueDTO.setIsRoot(catalogue.getIsRoot());
        catalogueDTO.setIsPrivate(catalogue.getIsPrivate());

        return catalogueDTO;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            catalogueRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new CatalogueNotFoundException();
        }
    }
}
