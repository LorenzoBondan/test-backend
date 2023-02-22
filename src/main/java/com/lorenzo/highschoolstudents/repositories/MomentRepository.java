package com.lorenzo.highschoolstudents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorenzo.highschoolstudents.entities.Moment;

@Repository
public interface MomentRepository extends JpaRepository<Moment,Long>{

}
