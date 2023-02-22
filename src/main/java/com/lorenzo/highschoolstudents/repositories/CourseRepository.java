package com.lorenzo.highschoolstudents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorenzo.highschoolstudents.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{

}
