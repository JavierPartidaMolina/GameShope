package com.proyectoSegunda.Javier.proyectoJavier.Service;

import java.util.List;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.User;
import com.proyectoSegunda.Javier.proyectoJavier.Model.UserModel;

public interface UsersServicePlus {
	
	public abstract User findUsersByUsername(String username);
	public abstract List<UserModel> ListAllUsers();
	public abstract UserModel findUsersByUsernameModel(String username);
}
