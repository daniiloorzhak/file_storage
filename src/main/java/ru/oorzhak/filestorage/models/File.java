package ru.oorzhak.filestorage.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "file")
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(max = 20)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "account_file",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Account creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "catalogue_file",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "catalogue_id"))
    private Catalogue catalogue;
}
