package com.interview.finra.controller;

import com.interview.finra.domain.FileMetadata;
import com.interview.finra.domain.FileUploadResponse;
import com.interview.finra.service.FileMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    private final FileMetadataService fileMetadataService;

    @Autowired
    public FileController(FileMetadataService fileMetadataService) {
        this.fileMetadataService = fileMetadataService;
    }

    /**
     * This Rest endpoint persist the file to file system and file metadata to H2 database (inbuilt).
     *
     * @param file     - Actual file which needs to be persisted.
     * @param metadata - File Metadata
     * @return FileUploadResponse - An unique identifier for the persisted file.
     * @throws Exception - I have implemented an @ControllerAdvice - exception handler. Have a look at @link{ControllerExceptionHandler.java}
     */
    @PutMapping(path = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FileUploadResponse uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestPart("file-metadata") FileMetadata metadata) throws Exception {
        //Note: For this interview coding purpose i have used tomcat's default max file upload size (i.e) 1MB.
        return fileMetadataService.processFileUpload(file, metadata);
    }

    /**
     * This Rest endpoint retrieves the metadata for the file. It accepts a file identifier and returns the metadata in json format.
     *
     * @param fileId - Unique file identifier.
     * @return File Metadata
     */
    @GetMapping(path = "/files/{fileId}/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public FileMetadata getFileMetadata(@PathVariable Long fileId) {
        //This file id is the unique identifier which got generated during Metadata persist which is a sub operation of above API.
        //During persist operation, on successful persist i return back an identifier for that metadata.
        //For this metadata search i have used that identifier; also search of metadata by file name is also a good use case.
        return fileMetadataService.retrieveFileMetaData(fileId);
    }
}
