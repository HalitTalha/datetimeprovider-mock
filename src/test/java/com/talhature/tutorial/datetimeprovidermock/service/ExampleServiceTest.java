package com.talhature.tutorial.datetimeprovidermock.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.talhature.tutorial.datetimeprovidermock.model.ExampleEntity;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class ExampleServiceTest {

	@Autowired
	private ExampleService service;

	@MockBean
	private DateTimeProvider dateTimeProvider;

	@SpyBean
	private AuditingHandler handler;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		handler.setDateTimeProvider(dateTimeProvider);
	}

	@Test
	void exampleServiceTest_getExampleByCreatedDate_OK() {
		ExampleEntity example = new ExampleEntity();
		example.setDescription("simple description");

		LocalDateTime createdDateTime = createLocalDateTime("2020-10-17 00:00");

		when(dateTimeProvider.getNow()).thenReturn(Optional.of(createdDateTime));
		service.saveExample(example);

		List<ExampleEntity> selectedList = service.findByCreatedDateTime(createdDateTime);
		assertTrue(selectedList.stream().allMatch(item -> createdDateTime.equals(item.getCreatedDateTime())));
	}

	@Test
	void exampleServiceTest_getExampleByUpdateDateTime_OK() {
		ExampleEntity example = new ExampleEntity();
		example.setDescription("simple description");

		LocalDateTime createdDateTime = createLocalDateTime("2020-10-17 00:00");

		when(dateTimeProvider.getNow()).thenReturn(Optional.of(createdDateTime));
		service.saveExample(example);

		LocalDateTime updateDateTime = createLocalDateTime("2099-11-23 05:00");
		when(dateTimeProvider.getNow()).thenReturn(Optional.of(updateDateTime));
		example.setDescription("changed description");
		service.saveExample(example);

		List<ExampleEntity> selectedList = service.findByUpdateDateTime(updateDateTime);
		assertTrue(selectedList.stream().allMatch(item -> updateDateTime.equals(item.getUpdateDateTime())));
	}

	private LocalDateTime createLocalDateTime(String date) {
		return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

}
