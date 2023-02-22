package com.lorenzo.highschoolstudents.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorenzo.highschoolstudents.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	// METODO QUE BUSCA NO BANCO UM USUARIO POR EMAIL
	User findByEmail(String email);
	
	// BUSCAR TODOS OS USUÁRIOS QUE SE CHAMAM MARIA, EXEMPLO
	//List<User> findByFirstName(String firstName);
}
