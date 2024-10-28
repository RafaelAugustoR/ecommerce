package com.rafaelaugusto.ecommerce.services;

import com.rafaelaugusto.ecommerce.domain.entities.User;
import com.rafaelaugusto.ecommerce.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private UserService userService;
	
	public void validateSelfOrAdmin(long userId) {
		User me = userService.authenticated();
		if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
			throw new ForbiddenException("Access denied");
		}
	}
}
