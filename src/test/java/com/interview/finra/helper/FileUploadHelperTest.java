package com.interview.finra.helper;

import com.interview.finra.dao.FileMetadataRepository;
import com.interview.finra.domain.FileMetadataEntity;
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
public class FileUploadHelperTest {

    @Autowired
    private FileMetadataRepository fileMetadataRepository;

    FileMetadataEntity testMetadataEntity = null;
    FileUploadHelper fileUploadHelper = null;
    MockMultipartFile mockMultipartFile = null;


    @Before
    public void setUp() {
        //create a test metadata
        testMetadataEntity = new FileMetadataEntity("test-name", "test-dept", "test-type", "test-user");
        //Create the test upload directory and that location is return so that i could be passed to next layer
        String filePersistLocation = FileUploadTestUtil.createTestUploadDirectoryIfDoesntExists();
        //create the helper
        fileUploadHelper = new FileUploadHelper(fileMetadataRepository, filePersistLocation);

        mockMultipartFile = new MockMultipartFile("file", "test-upload.txt", "text/plain", "This is a test content".getBytes());
    }

    @Test
    public void testPersistFileMetadataToDB_Success() {
        //if the persist is success ful the auto generated Entity id will be returned back.
        Long id = fileUploadHelper.persistFileMetadataToDB(testMetadataEntity);
        assertNotNull(id);
    }

    @Test
    public void testPersistFileMetadataToDB_NullMetadata() {
        //for a null metadata, the returned value should be null, as there is a null check on resp file.
        Long id = fileUploadHelper.persistFileMetadataToDB(null);
        assertNull(id);
    }

    @Test
    public void testStoreFileToFileSystem_Success() throws Exception {
        //clean up the existing test file before you test file creation
        FileUploadTestUtil.deleteTestFile();

        fileUploadHelper.storeFileToFileSystem(mockMultipartFile);
        //check whether file exists
        assertTrue(FileUploadTestUtil.doesTestFileExists());
        //delete the just created test file
        FileUploadTestUtil.deleteTestFile();

    }

    @Test
    public void testStoreFileToFileSystem_NullFile() {

        fileUploadHelper.persistFileMetadataToDB(null);
        //file shouldn't exists
        assertFalse(FileUploadTestUtil.doesTestFileExists());
    }

}
