package com.interview.finra.converter;

import com.interview.finra.domain.FileMetadata;
import com.interview.finra.domain.FileMetadataEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileMetadataEntityToMetadataConverterTest {

    private FileMetadataToEntityConverter metadataToEntityConverter = null;
    private FileMetadataEntity expected = null;
    private FileMetadata testFileMetadata = null;

    @Before
    public void setUp() {
        metadataToEntityConverter = new FileMetadataToEntityConverter();
        expected = new FileMetadataEntity("test-name", "test-dept", "test-type", "test-createdby");
        testFileMetadata = new FileMetadata("test-name", "test-dept", "test-type", "test-createdby");
    }

    @Test
    public void testConvert() {
        FileMetadataEntity actual = metadataToEntityConverter.convert(testFileMetadata);
        //assertions
        assertNotNull(actual);
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getDepartment(), actual.getDepartment());
    }
}
