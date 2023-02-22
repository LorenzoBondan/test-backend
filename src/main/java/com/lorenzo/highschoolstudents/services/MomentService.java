package com.lorenzo.highschoolstudents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lorenzo.highschoolstudents.dto.MomentDTO;
import com.lorenzo.highschoolstudents.entities.Moment;
import com.lorenzo.highschoolstudents.repositories.MomentRepository;

@Service
public class MomentService {

	@Autowired
	private MomentRepository repository;

	@Transactional(readOnly = true)
	public Page<MomentDTO> findAllPaged(Pageable pageable) {
		Page<Moment> list = repository.findAll(pageable);
		return list.map(x -> new MomentDTO(x));
	}


}
