package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.UserRole;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.UserRoleJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.UsersJpaRepository;

@Service("UsersService")
public class UsersService implements UserDetailsService{
	
	@Autowired
	@Qualifier("UsersJpaRepository")
	private UsersJpaRepository UsersJpaRepository;

	@Autowired
	@Qualifier("UserRoleJpaRepository")
	private UserRoleJpaRepository UserRoleJpaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.proyectoSegunda.Javier.proyectoJavier.Entity.User user = UsersJpaRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAthorities(user.getUserRole());
		return buildUser(user,authorities);
	}

	private User buildUser(com.proyectoSegunda.Javier.proyectoJavier.Entity.User user, List<GrantedAuthority> athorities) {
		return new User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,athorities);
	}
	
	private List<GrantedAuthority> buildAthorities(Set<UserRole> userRoles){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		for(UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}
	
	 public void save(com.proyectoSegunda.Javier.proyectoJavier.Entity.User user) {
		 	BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		 	user.setEnabled(true);
	        user.setPassword(p.encode(user.getPassword()));
	        UsersJpaRepository.save(user);
	        UserRoleJpaRepository.save(new UserRole(user, "USER_ROLE"));
	}
	 
	 public void saveNoEncode(com.proyectoSegunda.Javier.proyectoJavier.Entity.User user) {
	        UsersJpaRepository.save(user);
	}
	
	 public void remove(com.proyectoSegunda.Javier.proyectoJavier.Entity.User user) {
	        UsersJpaRepository.delete(user);
	} 
}
