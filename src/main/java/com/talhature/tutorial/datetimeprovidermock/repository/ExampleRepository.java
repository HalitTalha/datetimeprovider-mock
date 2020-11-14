package com.talhature.tutorial.datetimeprovidermock.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talhature.tutorial.datetimeprovidermock.model.ExampleEntity;

public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {

	List<ExampleEntity> findByCreatedDateTime(LocalDateTime createdDateTime);

	List<ExampleEntity> findByUpdateDateTime(LocalDateTime createdDateTime);
}
