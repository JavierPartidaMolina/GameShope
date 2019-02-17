package com.proyectoSegunda.Javier.proyectoJavier.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.proyectoSegunda.Javier.proyectoJavier.Converter.UsersConverter;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.User;
import com.proyectoSegunda.Javier.proyectoJavier.Model.UserModel;
import com.proyectoSegunda.Javier.proyectoJavier.Repository.UsersJpaRepository;
import com.proyectoSegunda.Javier.proyectoJavier.Service.UsersServicePlus;

@Service("UsersServicePlusImpl")
public class UsersServicePlusImpl implements UsersServicePlus{

	@Autowired
	@Qualifier("UsersJpaRepository")
	private UsersJpaRepository UsersJpaRepository;
	
	@Autowired
	@Qualifier("UsersConverter")
	private UsersConverter UsersConverter;
	
	@Override
	public User findUsersByUsername(String username) {
		return UsersJpaRepository.findByUsername(username);
	}

	@Override
	public List<UserModel> ListAllUsers() {
		List<UserModel> usersList = new ArrayList<>();
		UsersJpaRepository.findAll().forEach(user ->{usersList.add(UsersConverter.entity2model(user));});
		return usersList;
	}

	@Override
	public UserModel findUsersByUsernameModel(String username) {
		return UsersConverter.entity2model(findUsersByUsername(username));
	}

}
