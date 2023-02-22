package com.lorenzo.highschoolstudents.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lorenzo.highschoolstudents.dto.CourseDTO;
import com.lorenzo.highschoolstudents.entities.Course;
import com.lorenzo.highschoolstudents.repositories.CourseRepository;
import com.lorenzo.highschoolstudents.services.exceptions.DataBaseException;
import com.lorenzo.highschoolstudents.services.exceptions.ResourceNotFoundException;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repository;

	@Transactional(readOnly = true)
	public Page<CourseDTO> findAllPaged(Pageable pageable) {
		Page<Course> list = repository.findAll(pageable);
		return list.map(x -> new CourseDTO(x));
	}

	@Transactional(readOnly = true)
	public CourseDTO findById(Long id) {
		Optional<Course> obj = repository.findById(id);
		Course entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		return new CourseDTO(entity);
	}

	@Transactional
	public CourseDTO insert(CourseDTO dto) {
		Course entity = new Course();
		entity.setName(dto.getName());
		entity.setArea(dto.getArea());
		entity = repository.save(entity);
		return new CourseDTO(entity);
	}

	@Transactional
	public CourseDTO update(Long id, CourseDTO dto) {
		try {
			Course entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity.setArea(dto.getArea());
			entity = repository.save(entity);
			return new CourseDTO(entity);
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
}
