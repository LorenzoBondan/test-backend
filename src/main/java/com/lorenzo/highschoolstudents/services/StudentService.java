package com.lorenzo.highschoolstudents.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lorenzo.highschoolstudents.dto.CourseDTO;
import com.lorenzo.highschoolstudents.dto.StudentDTO;
import com.lorenzo.highschoolstudents.entities.Course;
import com.lorenzo.highschoolstudents.entities.Student;
import com.lorenzo.highschoolstudents.repositories.CourseRepository;
import com.lorenzo.highschoolstudents.repositories.StudentRepository;
import com.lorenzo.highschoolstudents.services.exceptions.DataBaseException;
import com.lorenzo.highschoolstudents.services.exceptions.ResourceNotFoundException;

import org.springframework.data.domain.Sort;


@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private CourseRepository CourseRepository;

	@Transactional(readOnly = true)
	public Page<StudentDTO> findAllPaged(Long CourseId, String name, Pageable pageable) {
		List<Course> courses = (CourseId == 0) ? null : Arrays.asList(CourseRepository.getOne(CourseId));
		Page<Student> page = repository.find(courses, name, pageable);
		repository.findStudentsWithCourses(page.getContent()); 
		return page.map(x -> new StudentDTO(x, x.getCourses()));
	}

	@Transactional(readOnly = true)
	public StudentDTO findById(Long id) {
		Optional<Student> obj = repository.findById(id);
		Student entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		return new StudentDTO(entity, entity.getCourses());
	}

	@Transactional
	public StudentDTO insert(StudentDTO dto) {
		Student entity = new Student();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new StudentDTO(entity);
	}

	@Transactional
	public StudentDTO update(Long id, StudentDTO dto) {
		try {
			Student entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new StudentDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity Violation");
		}
	}

	private void copyDtoToEntity(StudentDTO dto, Student entity) {

		entity.setName(dto.getName());
		entity.setNickname(dto.getNickname());
		entity.setGraduated(dto.getGraduated());
		entity.setImgUrl(dto.getImgUrl());
		entity.setDescription(dto.getDescription());
		entity.setBirthDate(dto.getBirthDate());
		entity.setLocation(dto.getLocation());
		entity.setPostitUrl(dto.getPostitUrl());
		entity.setMusicName(dto.getMusicName());
		entity.setMusicAuthor(dto.getMusicAuthor());
		entity.setMusicImgUrl(dto.getMusicImgUrl());
		
		entity.getCourses().clear();

		for (CourseDTO catDto : dto.getCourses()) {
			Course Course = CourseRepository.getOne(catDto.getId());
			entity.getCourses().add(Course);
		}
	}
}
