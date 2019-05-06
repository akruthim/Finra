package com.interview.finra.converter;

import com.interview.finra.domain.FileMetadata;
import com.interview.finra.domain.FileMetadataEntity;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FileMetadataToEntityConverterTest {

    private FileMetadataToEntityConverter metadataToEntityConverter = null;
    private FileMetadata testFileMetadata = null;
    private FileMetadataEntity expected = null;

    @Before
    public void setUp() {
        metadataToEntityConverter = new FileMetadataToEntityConverter();
        testFileMetadata = new FileMetadata("test-name", "test-dept", "test-type", "test-createdby");
        expected = new FileMetadataEntity("test-name", "test-dept", "test-type", "test-createdby");
    }

    @Test
    public void testConvert() {
        FileMetadataEntity actual = metadataToEntityConverter.convert(testFileMetadata);

        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getType(), actual.getType());
    }

}
