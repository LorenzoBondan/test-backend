package com.lorenzo.highschoolstudents.resources;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.lorenzo.highschoolstudents.dto.DiaryDTO;
import com.lorenzo.highschoolstudents.services.DiaryService;

@RestController
@RequestMapping(value = "/diaries")
public class DiaryResource {

	@Autowired
	private DiaryService service;

	@GetMapping
	public ResponseEntity<Page<DiaryDTO>> findAll(Pageable pageable) {
		Page<DiaryDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}


	@GetMapping(value = "/{year}")
	public ResponseEntity<List<DiaryDTO>> findByYear(@PathVariable Long year) {
		List<DiaryDTO> dto = service.findDiaryByYear(year);
		return ResponseEntity.ok().body(dto);
	}

}
