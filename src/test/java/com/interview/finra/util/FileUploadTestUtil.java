package com.interview.finra.util;

import com.interview.finra.converter.FileMetadataToEntityConverter;
import com.interview.finra.dao.FileMetadataRepository;
import com.interview.finra.domain.FileMetadata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is the utility class which i created to support my test
 */
public class FileUploadTestUtil {

    private final static FileMetadataToEntityConverter metadataToEntityConverter = new FileMetadataToEntityConverter();

    public static boolean doesFileExists(String path) {
        return Files.exists(Paths.get(path));
    }

    public static void deleteFile(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long insertTestMetadata(FileMetadataRepository metadataRepository, FileMetadata metadata) {
        return metadataRepository.save(metadataToEntityConverter.convert(metadata)).getId();
    }
}
