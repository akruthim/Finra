package com.interview.finra.service;

import com.interview.finra.controller.FileController;
import com.interview.finra.converter.FileMetadataEntityToMetadataConverter;
import com.interview.finra.converter.FileMetadataToEntityConverter;
import com.interview.finra.dao.FileMetadataRepository;
import com.interview.finra.domain.FileMetadata;
import com.interview.finra.domain.FileMetadataEntity;
import com.interview.finra.domain.FileUploadResponse;
import com.interview.finra.exception.FileUploadFailedException;
import com.interview.finra.helper.FileUploadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class FileMetadataServiceImpl implements FileMetadataService {
    //this is a console logger, in real time i will use a file appender
    private final Logger logger = LoggerFactory.getLogger(FileMetadataServiceImpl.class);

    private final FileUploadHelper fileUploadHelper;
    private final FileMetadataToEntityConverter metadataToEntityConverter;
    private final FileMetadataEntityToMetadataConverter entityToMetadataConverter;
    private final FileMetadataRepository metadataRepository;

    @Autowired
    public FileMetadataServiceImpl(FileMetadataToEntityConverter metadataToEntityConverter, FileUploadHelper fileUploadHelper,
                                   FileMetadataRepository metadataRepository, FileMetadataEntityToMetadataConverter entityToMetadataConverter) {
        this.metadataToEntityConverter = metadataToEntityConverter;
        this.fileUploadHelper = fileUploadHelper;
        this.metadataRepository = metadataRepository;
        this.entityToMetadataConverter = entityToMetadataConverter;
    }

    /**
     * This method orchestrates the file upload operation.
     * A helper class is leveraged to perform sub operations. 1. Store the file metadata first 2. Save the file to the file system.
     * H2 Db is used for this coding exercise to persist the metadata
     * This helper design approach gives a greater flexibility. It establishes a good separation of concerns pattern and also
     * makes a class small and crisp.
     *
     * @param file
     * @param metadata
     * @return FileUploadResponse
     * @throws Exception
     */
    @Override
    public FileUploadResponse processFileUpload(MultipartFile file, FileMetadata metadata) throws Exception {

        if (file == null || metadata == null) return null;
        // Client metadata domain is not persisted as such. Here a separate DB domain have put up and necessary fields are copied from client metadata domain.
        // For this a converter is leveraged (adhering to adapter design pattern). This converter implementation helps in creating a loosely coupled layer.
        // Say any change to domain in future to domain conversion, only this converter layer gets affected, other two layers remains intact.
        FileMetadataEntity metadataEntity = metadataToEntityConverter.convert(metadata);
        //First i want to persist the Metadata, and then the actual file to file system.
        // By this, i would not store to file system when actual metadata persist is failed.
        // In real time i would also want to utilize transaction, for the sake of coding exercise simplicity i haven't utilized.
        Long fileId = fileUploadHelper.persistFileMetadataToDB(metadataEntity);
        if (fileId == null) {
            logger.info("Meta data persist in DB failed");
            throw new FileUploadFailedException();
        }
        //On successful metadata persistence, the actual file is saved to file system.
        fileUploadHelper.storeFileToFileSystem(file);
        return new FileUploadResponse(fileId);
    }

    /**
     * Fetches metadata corresponds to the identifier and returns the details.
     * @param fileId - Unique file identifier
     * @return  FileMetadata
     */
    @Override
    public FileMetadata retrieveFileMetaData(Long fileId) {
        //JPA is leveraged
        Optional<FileMetadataEntity> optionalFileMetadataEntity = metadataRepository.findById(fileId);
        if (optionalFileMetadataEntity.isPresent())
            return entityToMetadataConverter.convert(optionalFileMetadataEntity.get());
        else {
            logger.info("Could not find metadata for the file identifier");
            return null;
        }
    }

}