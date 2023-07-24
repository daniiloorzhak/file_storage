package ru.oorzhak.filestorage.dto.catalogue;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogueResponseDTO {
    private Long id;
    private String name;
    private String creatorUsername;
    private Boolean isRoot;
    private Boolean isPrivate;
}
