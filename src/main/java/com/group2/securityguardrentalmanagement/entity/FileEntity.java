package com.group2.securityguardrentalmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private int fileId;
    @Column(name = "category_file")
    private String categoryFile;
    @Column(name = "link_file")
    private String linkFile;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
}
