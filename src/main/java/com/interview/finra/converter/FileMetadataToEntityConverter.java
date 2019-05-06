package com.interview.finra.converter;

import com.interview.finra.domain.FileMetadata;
import com.interview.finra.domain.FileMetadataEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FileMetadataToEntityConverter implements Converter<FileMetadata, FileMetadataEntity> {

    @Override
    public FileMetadataEntity convert(FileMetadata metadata) {
        FileMetadataEntity metadataEntity = new FileMetadataEntity(metadata.getName(), metadata.getDepartment(), metadata.getType(), metadata.getCreatedBy());
        return metadataEntity;
    }
}
