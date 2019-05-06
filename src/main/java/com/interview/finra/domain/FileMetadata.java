package com.interview.finra.domain;

public class FileMetadata {

    private String name;
    private String department;
    private String type;
    private String createdBy;

    public FileMetadata(String name, String department, String type, String createdBy) {
        this.name = name;
        this.department = department;
        this.type = type;
        this.createdBy = createdBy;
    }

    public FileMetadata() { }

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
        return "FileMetadata{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", type='" + type + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
