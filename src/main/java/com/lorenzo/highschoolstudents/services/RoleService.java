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

import com.lorenzo.highschoolstudents.dto.RoleDTO;
import com.lorenzo.highschoolstudents.entities.Role;
import com.lorenzo.highschoolstudents.repositories.RoleRepository;
import com.lorenzo.highschoolstudents.services.exceptions.DataBaseException;
import com.lorenzo.highschoolstudents.services.exceptions.ResourceNotFoundException;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	@Transactional(readOnly = true)
	public Page<RoleDTO> findAllPaged(Pageable pageable) {
		Page<Role> list = repository.findAll(pageable);
		return list.map(x -> new RoleDTO(x));
	}

	@Transactional(readOnly = true)
	public RoleDTO findById(Long id) {
		Optional<Role> obj = repository.findById(id);
		Role entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		return new RoleDTO(entity);
	}

	@Transactional
	public RoleDTO insert(RoleDTO dto) {
		Role entity = new Role();
		entity.setAuthority(dto.getAuthority());
		entity = repository.save(entity);
		return new RoleDTO(entity);
	}

	@Transactional
	public RoleDTO update(Long id, RoleDTO dto) {
		try {
			Role entity = repository.getOne(id);
			entity.setAuthority(dto.getAuthority());
			entity = repository.save(entity);
			return new RoleDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}

		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity Violation");
		}
	}
}
