package ru.oorzhak.filestorage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {
    private Long id;
    private String name;
    private String creatorUsername;
    private Long catalogId;
}
