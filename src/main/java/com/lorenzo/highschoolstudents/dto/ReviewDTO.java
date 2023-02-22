package com.lorenzo.highschoolstudents.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.lorenzo.highschoolstudents.entities.Review;

public class ReviewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "invalid black field")
	private String text;
	private Long studentId;
	private UserDTO user;
	
	public ReviewDTO() {}
	
    public ReviewDTO(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public ReviewDTO(Long id, String text, Long studentId, UserDTO user) {
        this.id = id;
        this.text = text;
        this.studentId = studentId;
        this.user = user;
    }

    public ReviewDTO(Review entity) {
        this(entity.getId(), entity.getText(), entity.getStudent().getId(), new UserDTO(entity.getUser()));
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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
		ReviewDTO other = (ReviewDTO) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
