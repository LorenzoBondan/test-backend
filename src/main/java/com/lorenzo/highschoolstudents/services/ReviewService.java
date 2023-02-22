package com.lorenzo.highschoolstudents.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lorenzo.highschoolstudents.dto.ReviewDTO;
import com.lorenzo.highschoolstudents.entities.Review;
import com.lorenzo.highschoolstudents.entities.Student;
import com.lorenzo.highschoolstudents.entities.User;
import com.lorenzo.highschoolstudents.repositories.ReviewRepository;
import com.lorenzo.highschoolstudents.repositories.StudentRepository;
import com.lorenzo.highschoolstudents.services.exceptions.DataBaseException;
import com.lorenzo.highschoolstudents.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findReviewsByStudentId(Long StudentId) {
		Student Student = studentRepository.getOne(StudentId);
		return Student.getReviews().stream().map(review -> new ReviewDTO(review)).collect(Collectors.toList());
	}
	
	@Transactional
	public ReviewDTO insert(ReviewDTO reviewDTO) {
		User user = authService.authenticated(); // VERIFICAR SE É UM USUÁRIO VÁLIDO
		
		Review review = new Review();
		review.setText(reviewDTO.getText());
		review.setStudent(studentRepository.getOne(reviewDTO.getStudentId()));
		review.setUser(user);
			
		repository.save(review);
		return new ReviewDTO(review);
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
