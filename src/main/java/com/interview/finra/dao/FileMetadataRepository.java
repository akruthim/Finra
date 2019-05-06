package com.interview.finra.dao;

import com.interview.finra.domain.FileMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetadataRepository extends JpaRepository<FileMetadataEntity, Long> {
}
