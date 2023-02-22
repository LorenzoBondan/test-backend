package com.lorenzo.highschoolstudents.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorenzo.highschoolstudents.entities.Review;
import com.lorenzo.highschoolstudents.entities.Student;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{

	List<Review> findByStudent(Student student);
}
