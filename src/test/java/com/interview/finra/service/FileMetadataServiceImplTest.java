package com.interview.finra.service;

import com.interview.finra.converter.FileMetadataEntityToMetadataConverter;
import com.interview.finra.converter.FileMetadataToEntityConverter;
import com.interview.finra.dao.FileMetadataRepository;
import com.interview.finra.domain.FileMetadata;
import com.interview.finra.domain.FileUploadResponse;
import com.interview.finra.helper.FileUploadHelper;
import com.interview.finra.util.FileUploadTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileMetadataServiceImplTest {

    @Autowired
    private FileMetadataRepository fileMetadataRepository;

    FileMetadata testMetadata = null;
    FileUploadHelper fileUploadHelper = null;
    MockMultipartFile mockMultipartFile = null;
    FileMetadataServiceImpl fileMetadataService = null;

    @Before
    public void setUp() {
        //create a test metadata
        testMetadata = new FileMetadata("test-name", "test-dept", "test-type", "test-user");
        //Create the test upload directory and that location is return so that i could be passed to next layer
        String filePersistLocation = FileUploadTestUtil.createTestUploadDirectoryIfDoesntExists();
        //create the helper
        fileUploadHelper = new FileUploadHelper(fileMetadataRepository, filePersistLocation);

        mockMultipartFile = new MockMultipartFile("file", "test-upload.txt", "text/plain", "This is a test content".getBytes());
        //create the service instance
        fileMetadataService = new FileMetadataServiceImpl(new FileMetadataToEntityConverter(), fileUploadHelper, fileMetadataRepository, new FileMetadataEntityToMetadataConverter());
    }

    @Test
    public void testProcessFileUpload_success() throws Exception {
        //clean up prev test file if any before creating one, precaution
        //calling the utility class i created to support my test
        FileUploadTestUtil.deleteTestFile();
        FileUploadResponse uploadResponse = fileMetadataService.processFileUpload(mockMultipartFile, testMetadata);

        assertNotNull(uploadResponse);
        assertNotNull(uploadResponse.getFileId());
        //flush after test
        FileUploadTestUtil.deleteTestFile();
    }

    @Test
    public void testProcessFileUpload_file_null() throws Exception {
        FileUploadResponse uploadResponse = fileMetadataService.processFileUpload(null, testMetadata);
        assertNull(uploadResponse);
    }

    @Test
    public void testProcessFileUpload_file_metadata_null() throws Exception {
        FileUploadResponse uploadResponse = fileMetadataService.processFileUpload(mockMultipartFile, null);
        assertNull(uploadResponse);
    }

    @Test
    public void testRetrieveFileMetaData_validFileID() {
        //before retrieving metadata, i am inserting a test metadata to DB and retrieving that back.
        Long fileID = FileUploadTestUtil.insertTestMetadata(fileMetadataRepository, testMetadata);
        FileMetadata actual = fileMetadataService.retrieveFileMetaData(fileID);

        assertNotNull(actual);
        assertEquals(testMetadata.getName(), actual.getName());
        assertEquals(testMetadata.getCreatedBy(), actual.getCreatedBy());
    }

    @Test
    public void testRetrieveFileMetaData_InValidFileID() {
        //if i sent a invalid file id which than i should receive a null, sending 0
        FileMetadata actual = fileMetadataService.retrieveFileMetaData(0L);
        assertNull(actual);
    }
}
