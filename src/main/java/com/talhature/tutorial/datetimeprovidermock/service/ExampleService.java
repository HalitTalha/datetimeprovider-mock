package com.talhature.tutorial.datetimeprovidermock.service;

import java.time.LocalDateTime;
import java.util.List;

import com.talhature.tutorial.datetimeprovidermock.model.ExampleEntity;

public interface ExampleService {

	ExampleEntity saveExample(ExampleEntity example);

	List<ExampleEntity> findByCreatedDateTime(LocalDateTime datetime);

	List<ExampleEntity> findByUpdateDateTime(LocalDateTime datetime);

}
