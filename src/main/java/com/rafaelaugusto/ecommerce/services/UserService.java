package com.rafaelaugusto.ecommerce.services;

import com.rafaelaugusto.ecommerce.domain.entities.Role;
import com.rafaelaugusto.ecommerce.domain.entities.User;
import com.rafaelaugusto.ecommerce.projections.UserDetailsProjection;
import com.rafaelaugusto.ecommerce.repositories.UserRepository;
import com.rafaelaugusto.ecommerce.rest.dtos.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
		if (result.size() == 0) {
			throw new UsernameNotFoundException("Email not found");
		}
		
		User user = new User();
		user.setEmail(result.get(0).getUsername());
		user.setPassword(result.get(0).getPassword());
		for (UserDetailsProjection projection : result) {
			user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}
		
		return user;
	}
	
	protected User authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			return repository.findByEmail(username).get();
		}
		catch (Exception e) {
			throw new UsernameNotFoundException("Invalid user");
		}
	}
	
	@Transactional(readOnly = true)
	public UserResponseDTO getMe() {
		User entity = authenticated();
		return new UserResponseDTO(entity);
	}
}
