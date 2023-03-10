package com.lorenzo.highschoolstudents.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lorenzo.highschoolstudents.dto.ReviewDTO;
import com.lorenzo.highschoolstudents.dto.StudentDTO;
import com.lorenzo.highschoolstudents.services.ReviewService;
import com.lorenzo.highschoolstudents.services.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {

	@Autowired
	private StudentService service;
	
	@Autowired
	private ReviewService reviewService;
	

	@GetMapping
	public ResponseEntity<Page<StudentDTO>> findAll(
			@RequestParam(value = "courseId", defaultValue = "0") Long courseId, 
			@RequestParam(value = "name", defaultValue = "") String name, 
			Pageable pageable) {
		Page<StudentDTO> list = service.findAllPaged(courseId, name.trim(), pageable);	
		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
		StudentDTO dto = service.findById(id);	
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<StudentDTO> insert (@Valid @RequestBody StudentDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> update(@PathVariable Long id, @Valid @RequestBody StudentDTO dto)	{
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> delete(@PathVariable Long id)	{
		service.delete(id);
		return ResponseEntity.noContent().build(); 
	}
	
	@GetMapping(path = "/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> findReviewsByStudentId(@PathVariable Long id) {
		List<ReviewDTO> reviewsDTO = reviewService.findReviewsByStudentId(id);
		return ResponseEntity.ok(reviewsDTO);
	}
	

}
