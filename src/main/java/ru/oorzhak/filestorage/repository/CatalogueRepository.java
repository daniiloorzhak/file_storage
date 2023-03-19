package ru.oorzhak.filestorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oorzhak.filestorage.models.Catalogue;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
}