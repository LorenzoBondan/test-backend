package com.lorenzo.highschoolstudents.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.lorenzo.highschoolstudents.entities.Contact;
import com.lorenzo.highschoolstudents.entities.Course;
import com.lorenzo.highschoolstudents.entities.Student;

public class StudentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "Campo obrigatório")
	private String name;
	private String nickname;
	private Boolean graduated;
	private String imgUrl;
	
	private Instant birthDate;
	private String description;
	private String location;
	private String postitUrl;
	
	private String musicName;
	private String musicAuthor;
	private String musicImgUrl;
	
	private Contact contact;
	
	private List<CourseDTO> courses = new ArrayList<>();
	
	
	public StudentDTO() {}


	public StudentDTO(Long id, String name, String nickname, Integer age,
			Boolean graduated, String imgUrl, String description, Instant birthDate, String location, String postitUrl, 
			String musicName, String musicAuthor, String musicImgUrl, Contact contact) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.graduated = graduated;
		this.imgUrl = imgUrl;
		this.description = description;
		this.birthDate = birthDate;
		this.location = location;
		this.postitUrl = postitUrl;
		this.musicName = musicName;
		this.musicAuthor = musicAuthor;
		this.musicImgUrl = musicImgUrl;
		this.contact = contact;
	}
	
	public StudentDTO(Student entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.nickname = entity.getNickname();
		this.graduated = entity.getGraduated();
		this.imgUrl = entity.getImgUrl();
		this.description = entity.getDescription();
		this.birthDate = entity.getBirthDate();
		this.location = entity.getLocation();
		this.postitUrl = entity.getPostitUrl();
		this.musicName = entity.getMusicName();
		this.musicAuthor = entity.getMusicAuthor();
		this.musicImgUrl = entity.getMusicImgUrl();
		this.contact = entity.getContact();
	}
	
	public StudentDTO(Student entity, Set<Course> courses) {
		this(entity); 
		courses.forEach(c -> this.courses.add(new CourseDTO(c)));
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


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public Boolean getGraduated() {
		return graduated;
	}


	public void setGraduated(Boolean graduated) {
		this.graduated = graduated;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	


	public Instant getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}
	

	public String getPostitUrl() {
		return postitUrl;
	}

	public void setPostitUrl(String postitUrl) {
		this.postitUrl = postitUrl;
	}
	
	

	public String getMusicName() {
		return musicName;
	}


	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}


	public String getMusicAuthor() {
		return musicAuthor;
	}


	public void setMusicAuthor(String musicAuthor) {
		this.musicAuthor = musicAuthor;
	}


	public String getMusicImgUrl() {
		return musicImgUrl;
	}


	public void setMusicImgUrl(String musicImgUrl) {
		this.musicImgUrl = musicImgUrl;
	}


	public List<CourseDTO> getCourses() {
		return courses;
	}


	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
}
