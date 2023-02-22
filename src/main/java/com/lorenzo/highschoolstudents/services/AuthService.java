package com.lorenzo.highschoolstudents.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lorenzo.highschoolstudents.entities.User;
import com.lorenzo.highschoolstudents.repositories.UserRepository;
import com.lorenzo.highschoolstudents.services.exceptions.ForbiddenException;
import com.lorenzo.highschoolstudents.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName(); // PEGA O NOME DO USUÁRIO QUE JÁ FOI RECONHECIDO PELO SPRING SECURITY
			return userRepository.findByEmail(username);
		}
		catch(Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}
	
	
	public void validateSelfOrAdmin(Long userId) { // VERIFICAR SE O USUÁRIO INFORMADO É O QUE ESTÁ LOGADO OU É ADMIN, SE SIM, VAI PASSAR, SE NÃO, GERA EXCEÇÃO
		User user = authenticated();
		
		if(!user.getId().equals(userId) && !user.hasRole("ROLE_OPERATOR")) { //NÃO FOR OPERADOR
			throw new ForbiddenException("Access denied");
		}
	}
}
