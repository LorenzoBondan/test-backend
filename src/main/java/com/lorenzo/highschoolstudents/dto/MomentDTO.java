package com.lorenzo.highschoolstudents.dto;

import java.io.Serializable;
import java.util.Objects;

import com.lorenzo.highschoolstudents.entities.Moment;

public class MomentDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String description;
	private String date;
	private String imgUrl;
	
	public MomentDTO() {}

	public MomentDTO(Long id, String title,String description, String date, String imgUrl) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.imgUrl = imgUrl;
		this.title = title;
	}
	
	public MomentDTO(Moment entity) {
		this.id=entity.getId();
		this.description=entity.getDescription();
		this.date=entity.getDate();
		this.imgUrl=entity.getImgUrl();
		this.title = entity.getTitle();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		MomentDTO other = (MomentDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
