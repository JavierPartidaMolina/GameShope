package com.proyectoSegunda.Javier.proyectoJavier.Converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.User;
import com.proyectoSegunda.Javier.proyectoJavier.Model.UserModel;


@Component("UsersConverter")
public class UsersConverter {
	
	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public UserModel entity2model(User user) {
		return mapper.map(user,UserModel.class);
	}
	public User model2entity(UserModel usermodel) {
		return mapper.map(usermodel, User.class);
	}
}

