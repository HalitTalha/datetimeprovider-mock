package com.talhature.tutorial.datetimeprovidermock.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class ExampleEntity {

	@Id
	@GeneratedValue
	private long id;

	@Version
	private int version;

	@CreatedDate
	private LocalDateTime createdDateTime;

	@LastModifiedDate
	private LocalDateTime updateDateTime;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdDateTime, description, updateDateTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExampleEntity other = (ExampleEntity) obj;
		return Objects.equals(createdDateTime, other.createdDateTime) && Objects.equals(description, other.description)
				&& Objects.equals(updateDateTime, other.updateDateTime);
	}

}
