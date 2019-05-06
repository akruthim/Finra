package com.interview.finra.domain;


import javax.persistence.*;

@Entity
@Table(name = "FILE_METADATA")
public class FileMetadataEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String department;
    @Column
    private String type;
    @Column
    private String createdBy;

    public FileMetadataEntity(String name, String department, String type, String createdBy) {
        this.name = name;
        this.department = department;
        this.type = type;
        this.createdBy = createdBy;
    }

    public FileMetadataEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "FileMetadataEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", type='" + type + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
