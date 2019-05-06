package com.interview.finra.domain;

public class FileUploadResponse {

    private final Long fileId;

    public FileUploadResponse(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileId() {
        return fileId;
    }

    @Override
    public String toString() {
        return "FileUploadResponse{" +
                "fileId=" + fileId +
                '}';
    }
}
