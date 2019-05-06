package com.interview.finra.helper;

import com.interview.finra.dao.FileMetadataRepository;
import com.interview.finra.domain.FileMetadataEntity;
import com.interview.finra.exception.FileUploadFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileUploadHelper {
    //for the simplicity, this logs to console
    private final Logger logger = LoggerFactory.getLogger(FileUploadHelper.class);

    private final String fileLocation;
    private FileMetadataRepository fileMetadataRepository;

    @Autowired
    public FileUploadHelper(FileMetadataRepository fileMetadataRepository, @Value("${file.persist.location}") String fileLocation) {
        this.fileLocation = fileLocation;
        this.fileMetadataRepository = fileMetadataRepository;
    }

    /**
     * Persist metadata to DB
     * @param metadataEntity
     * @return
     */
    public Long persistFileMetadataToDB(FileMetadataEntity metadataEntity) {
        if (metadataEntity == null) return null;
        fileMetadataRepository.save(metadataEntity);
        return metadataEntity.getId();
    }

    /**
     * Stores file to file system
     * @param file
     * @throws Exception
     */
    public void storeFileToFileSystem(MultipartFile file) throws Exception {

        try {
            Files.write(Paths.get(fileLocation + file.getOriginalFilename()), file.getBytes());
        } catch (IOException e) {
            logger.error("Exception occurred while storing file", e);
            //These exception's are handled by a common handler which i have implemented. Have a look at ControllerExceptionHandler.java
            throw new FileUploadFailedException();
        } catch (Exception e) {
            logger.error("Exception occurred while storing file", e);
            throw new FileUploadFailedException();
        }
    }

}
