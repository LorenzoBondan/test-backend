package com.lorenzo.highschoolstudents.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_student")
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String nickname;

	private Boolean graduated;
	@Column(columnDefinition = "TEXT")
	private String imgUrl;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant birthDate;
	
	private String location;
	
	@Column(columnDefinition = "TEXT")
	private String postitUrl;
	
	private String musicName;
	private String musicAuthor;
	@Column(columnDefinition = "TEXT")
	private String musicImgUrl;
	
	
	@OneToOne
	@JoinColumn(name = "contact_id")
	private Contact contact;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_student_course",
				joinColumns = @JoinColumn(name = "student_id"),
				inverseJoinColumns = @JoinColumn(name = "course_id")
			)
	private Set<Course> courses = new HashSet<>();
	
	@OneToMany(mappedBy = "student")
	private List<Review> reviews = new ArrayList<>();
	
	public Student() {}

	public Student(Long id, String name, String nickname, Integer age, Boolean graduated, String imgUrl, String description, Instant birthDate, String location, String postitUrl,
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
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public String getLocation() {
		return location;
	}
	

	public String getPostitUrl() {
		return postitUrl;
	}

	public void setPostitUrl(String postitUrl) {
		this.postitUrl = postitUrl;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Set<Course> getCourses() {
		return courses;
	}
	

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	

	public List<Review> getReviews() {
		return reviews;
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
		Student other = (Student) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
