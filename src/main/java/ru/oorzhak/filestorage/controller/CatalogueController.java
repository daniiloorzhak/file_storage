package ru.oorzhak.filestorage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oorzhak.filestorage.dto.catalogue.CatalogueChildDTO;
import ru.oorzhak.filestorage.dto.catalogue.CatalogueResponseDTO;
import ru.oorzhak.filestorage.dto.catalogue.CatalogueRootDTO;
import ru.oorzhak.filestorage.repository.UserRepository;
import ru.oorzhak.filestorage.service.CatalogueService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "catalogues", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatalogueController {
    private final CatalogueService catalogueService;
    private final UserRepository userRepository;

    @Autowired
    public CatalogueController(CatalogueService catalogueService,
                               UserRepository userRepository) {
        this.catalogueService = catalogueService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<CatalogueResponseDTO>> getRootCatalogues (@RequestParam(required = false) Long creatorId) {
        List<CatalogueResponseDTO> catalogueDTOS;
        if (creatorId != null) {
            String creatorUsername = userRepository.findById(creatorId).get().getUsername(); // exception
            catalogueDTOS = catalogueService.findAll().stream()
                    .filter(catalogueDTO -> creatorUsername.equals(catalogueDTO.getCreatorUsername()))
                    .toList();
            return ResponseEntity.ok(catalogueDTOS);
        }
        catalogueDTOS = catalogueService.findAll();
        return ResponseEntity.ok(catalogueDTOS);
    }

    @PostMapping
    public ResponseEntity<Long> createRootCatalogue(@Valid @RequestBody CatalogueRootDTO catalogueRootDTO) {
        return ResponseEntity.ok(catalogueService.save(catalogueRootDTO).getId());
    }

    @PostMapping("{parentId}")
    public ResponseEntity<Long> createChildCatalogue(@PathVariable Long parentId, @Valid @RequestBody CatalogueChildDTO catalogueChildDTO) {
        return ResponseEntity.ok(catalogueService.save(catalogueChildDTO, parentId).getId());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<CatalogueResponseDTO> getCatalogueDetails(@PathVariable Long id) {
        return ResponseEntity.ok(catalogueService.findById(id));
    }

    @GetMapping("{childId}/parent")
    public ResponseEntity<CatalogueResponseDTO> getParent(@PathVariable Long childId) {
        return ResponseEntity.ok(catalogueService.findParent(childId));
    }

    @GetMapping("{parentId}/children")
    public ResponseEntity<List<CatalogueResponseDTO>> getChildren(@PathVariable Long parentId) {
        return ResponseEntity.ok(catalogueService.findChildren(parentId));
    }

    @PutMapping("{id}")
    public ResponseEntity<Long> renameCatalogue(@PathVariable Long id, @RequestBody String newName) {
        return ResponseEntity.ok(catalogueService.renameDirectory(id, newName).getId());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCatalogue(@PathVariable Long id) {
        catalogueService.delete(id);
        return ResponseEntity.ok().build();
    }
}