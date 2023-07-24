package ru.oorzhak.filestorage.dto.catalogue;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogueRootDTO {
    private String name;
    private Boolean isPrivate;
}
