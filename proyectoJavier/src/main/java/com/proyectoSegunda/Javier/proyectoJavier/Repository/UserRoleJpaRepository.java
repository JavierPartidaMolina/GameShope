package com.proyectoSegunda.Javier.proyectoJavier.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.User;
import com.proyectoSegunda.Javier.proyectoJavier.Entity.UserRole;

@Repository("UserRoleJpaRepository")
public interface UserRoleJpaRepository extends JpaRepository<UserRole, Serializable> {

}
