package com.lorenzo.highschoolstudents.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lorenzo.highschoolstudents.entities.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM tb_diary WHERE year LIKE :year")
	List<Diary> searchByYear(Long year);
}
