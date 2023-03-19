package ru.oorzhak.filestorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oorzhak.filestorage.models.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
