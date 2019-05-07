package com.interview.finra.util;

import com.interview.finra.converter.FileMetadataToEntityConverter;
import com.interview.finra.dao.FileMetadataRepository;
import com.interview.finra.domain.FileMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is the utility class which i created to support my test
 */
public class FileUploadTestUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileUploadTestUtil.class);
    private final static FileMetadataToEntityConverter metadataToEntityConverter = new FileMetadataToEntityConverter();

    private final static String TEST_UPLOAD_DIRECTORY = Paths.get("").toAbsolutePath().toString() + "/src/test/uploads/";
    private final static String TEST_UPLOAD_FILE = TEST_UPLOAD_DIRECTORY + "/test-upload.txt";

    public static boolean doesTestFileExists() {
        return Files.exists(Paths.get(TEST_UPLOAD_FILE));
    }

    public static void deleteTestFile() {
        try {
            Files.deleteIfExists(Paths.get(TEST_UPLOAD_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long insertTestMetadata(FileMetadataRepository metadataRepository, FileMetadata metadata) {
        return metadataRepository.save(metadataToEntityConverter.convert(metadata)).getId();
    }

    public static String createTestUploadDirectoryIfDoesntExists() {
        if (!doesTestFileExists()) {
            try {
                Files.createDirectory(Paths.get(TEST_UPLOAD_DIRECTORY));
            } catch (IOException e) {
                logger.error("Exception occurred while creating test upload directory");
            }
        }
        return TEST_UPLOAD_DIRECTORY;
    }
}
