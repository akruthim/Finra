package com.interview.finra.converter;

import com.interview.finra.domain.FileMetadata;
import com.interview.finra.domain.FileMetadataEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FileMetadataEntityToMetadataConverter implements Converter<FileMetadataEntity, FileMetadata> {

    @Override
    public FileMetadata convert(FileMetadataEntity metadataEntity) {

        FileMetadata metadata = new FileMetadata(metadataEntity.getName(), metadataEntity.getDepartment(), metadataEntity.getType(), metadataEntity.getCreatedBy());
        return metadata;
    }
}
