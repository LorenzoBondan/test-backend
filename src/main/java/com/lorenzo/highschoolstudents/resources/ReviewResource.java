package com.lorenzo.highschoolstudents.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lorenzo.highschoolstudents.dto.ReviewDTO;
import com.lorenzo.highschoolstudents.dto.StudentDTO;
import com.lorenzo.highschoolstudents.services.ReviewService;

@RestController
@RequestMapping(path = "/reviews")
public class ReviewResource {

	@Autowired
	private ReviewService service;
	
	@PreAuthorize("hasAnyRole('OPERATOR')") // SOMENTE OS OPERATORS PODEM USAR ESSE ENDPOINT
	@PostMapping
	public ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewDTO reviewDTO) {
		reviewDTO = service.insert(reviewDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(reviewDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(reviewDTO);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> delete(@PathVariable Long id)	{
		service.delete(id);
		return ResponseEntity.noContent().build(); 
	}

}
