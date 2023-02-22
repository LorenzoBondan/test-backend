package com.lorenzo.highschoolstudents.dto;

import java.io.Serializable;
import java.util.Objects;

import com.lorenzo.highschoolstudents.entities.Diary;


public class DiaryDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long year;
	private String description;
	private String date;
	
	public DiaryDTO() {}

	public DiaryDTO(Long id, Long year, String description, String date) {
		super();
		this.id = id;
		this.year = year;
		this.description = description;
		this.date = date;
	}
	
	public DiaryDTO(Diary entity) {
		this.id = entity.getId();
		this.year = entity.getYear();
		this.description = entity.getDescription();
		this.date = entity.getDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiaryDTO other = (DiaryDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
