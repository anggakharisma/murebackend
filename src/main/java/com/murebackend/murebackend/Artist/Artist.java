package com.murebackend.murebackend.Artist;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
public class Artist {
	private Long id;
	@NotBlank(message = "name is required")
	private String name;

	@NotBlank(message = "description is required")
	private String description;

	private String alias;

	private String imagePath;

	private Date createdAt;
	private Date updatedAt;

	public Artist() {
	}

	public Artist(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Artist(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
