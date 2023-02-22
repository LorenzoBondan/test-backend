package com.lorenzo.highschoolstudents.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lorenzo.highschoolstudents.dto.DiaryDTO;
import com.lorenzo.highschoolstudents.entities.Diary;
import com.lorenzo.highschoolstudents.repositories.DiaryRepository;

@Service
public class DiaryService {

	@Autowired
	private DiaryRepository repository;

	@Transactional(readOnly = true)
	public Page<DiaryDTO> findAllPaged(Pageable pageable) {
		Page<Diary> list = repository.findAll(pageable);
		return list.map(x -> new DiaryDTO(x));
	}

	
	@Transactional(readOnly = true)
	public List<DiaryDTO> findDiaryByYear(Long year) {
		List<Diary> diary = repository.searchByYear(year);
		return diary.stream().map(d -> new DiaryDTO(d)).collect(Collectors.toList());
	}

}
