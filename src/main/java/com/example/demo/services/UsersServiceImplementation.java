package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UserRepositories;

@Service
public class UsersServiceImplementation implements UsersService
{
	@Autowired
	UserRepositories usrepo;

	@Override
	public String addUser(Users user) {
		usrepo.save(user);
		return "User is created and saved";
	}

	@Override
	public boolean emailExists(String email) {
		
		if(usrepo.findByEmail(email)==null)
		{
			return false;
		}
		else
		{
		     return true; 	
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user = usrepo.findByEmail(email);
		String db_password=user.getPassword();
		if(db_password.equals(password))
		{
			return true;
		}
		else {
			return false;	
		}
		
	}

	@Override
	public String getRole(String email) {
		 return (usrepo.findByEmail(email).getRole());
	}

	@Override
	public Users getUser(String email) {
		
		return usrepo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		
		usrepo.save(user);
	}

}
