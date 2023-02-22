package com.lorenzo.highschoolstudents.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzo.highschoolstudents.dto.MomentDTO;
import com.lorenzo.highschoolstudents.services.MomentService;

@RestController
@RequestMapping(value = "/moments")
public class MomentResource {

	@Autowired
	private MomentService service;

	@GetMapping
	public ResponseEntity<Page<MomentDTO>> findAll(Pageable pageable) {
		Page<MomentDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

}
