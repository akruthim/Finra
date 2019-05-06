package com.interview.finra.dao;

import com.interview.finra.domain.FileMetadataEntity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileMetadataRepositoryImplTest {

    //auto-wiring the File metadata JPA repository
    @Autowired
    private FileMetadataRepository fileMetadataRepository;
    private FileMetadataEntity testMetadataEntity = null;

    @Before
    public void setUp() {
        //create a test metadata
        testMetadataEntity = new FileMetadataEntity("test-name", "test-dept", "test-type", "test-user");
    }

    @Test
    public void testPersistFileMetadata() {
        fileMetadataRepository.save(testMetadataEntity);
        //Metadata persist is successful if Id is not null. Id is auto generated; during persist hibernate generates one
        // and sets to the id field.
        assertNotNull(testMetadataEntity.getId());
    }

    @Test
    public void testRetrieveFileMetadata() {
        //To retrieve, i first persist a metadata and then same metadata is retrieved back.
        // If the actual test data value and the retrieved data are same, then by retrieval operation is successful.
        fileMetadataRepository.save(testMetadataEntity);
        Optional<FileMetadataEntity> actual = fileMetadataRepository.findById(testMetadataEntity.getId());

        assertTrue(actual.isPresent());
        assertEquals(testMetadataEntity.getId(), actual.get().getId());
    }

}
