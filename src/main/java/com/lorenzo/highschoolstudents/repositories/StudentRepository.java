package com.lorenzo.highschoolstudents.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lorenzo.highschoolstudents.entities.Course;
import com.lorenzo.highschoolstudents.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

	@Query("SELECT DISTINCT obj FROM Student obj "
			+ "	INNER JOIN obj.courses c "
			+ "WHERE (COALESCE(:courses) IS NULL OR c IN :courses) AND "
			+ "(UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%')) ) ORDER BY obj.name")
	Page<Student> find(List<Course> courses, String name, Pageable pageable); // METODO CRIADO PARA BUSCAR Students POR Courses, ULTIMO PARAMETRO TEM QUE SER UM PAGEABLE
	
	// PRA CORRIGIR O PROBLEMA DAS N+1 CONSULTAS
	@Query("SELECT obj FROM Student obj JOIN FETCH obj.courses WHERE obj IN :students") // JOIN FETCH BUSCA OS OBJETOS JUNTO COM O PRODUTO. SÓ FUNCIONA COM LISTA E NAO COM PAGINA, POR ISSO A CONSULTA EM DUAS ETAPAS
	List<Student> findStudentsWithCourses(List<Student> students);
}
