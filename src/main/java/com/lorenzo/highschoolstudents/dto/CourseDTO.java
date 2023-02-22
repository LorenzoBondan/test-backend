package com.lorenzo.highschoolstudents.dto;

import java.io.Serializable;
import java.util.Objects;

import com.lorenzo.highschoolstudents.entities.Course;


public class CourseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String area;
	
	public CourseDTO() {}

	public CourseDTO(Long id, String name, String area) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
	}
	
	public CourseDTO(Course entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.area = entity.getArea();
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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
		CourseDTO other = (CourseDTO) obj;
		return Objects.equals(id, other.id);
	}
	


}
