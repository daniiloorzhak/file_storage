package ru.oorzhak.filestorage.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private Long id;
    private String name;
    private String creatorUsername;
    private Long catalogId;
}
