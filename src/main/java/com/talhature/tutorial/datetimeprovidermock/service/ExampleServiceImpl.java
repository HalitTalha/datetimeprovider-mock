package com.talhature.tutorial.datetimeprovidermock.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talhature.tutorial.datetimeprovidermock.model.ExampleEntity;
import com.talhature.tutorial.datetimeprovidermock.repository.ExampleRepository;

@Service
public class ExampleServiceImpl implements ExampleService {

	@Autowired
	private ExampleRepository repository;

	@Override
	public ExampleEntity saveExample(ExampleEntity example) {
		return repository.save(example);
	}

	@Override
	public List<ExampleEntity> findByCreatedDateTime(LocalDateTime createdDateTime) {
		return repository.findByCreatedDateTime(createdDateTime);
	}

	@Override
	public List<ExampleEntity> findByUpdateDateTime(LocalDateTime updateDateTime) {
		return repository.findByUpdateDateTime(updateDateTime);
	}

}
