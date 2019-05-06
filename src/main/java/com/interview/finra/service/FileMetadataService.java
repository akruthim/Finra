package com.interview.finra.service;

import com.interview.finra.domain.FileMetadata;
import com.interview.finra.domain.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileMetadataService {

    FileUploadResponse processFileUpload(MultipartFile file, FileMetadata metadata) throws Exception;

    FileMetadata retrieveFileMetaData(Long fileId);
}
