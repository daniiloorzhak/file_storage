package ru.oorzhak.filestorage.dto.catalogue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogueResponseDTO {
    private Long id;
    private String name;
    private String creatorUsername;
    private Boolean isRoot;
    private Boolean isPrivate;
}
