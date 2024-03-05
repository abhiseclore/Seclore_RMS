package com.seclore.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seclore.main.domain.UserDetails;
import com.seclore.main.repository.UserDetailsRepositoryInterface;

@Service
public class UserDetailsService implements UserDetailsServiceInterface {

	@Autowired
	UserDetailsRepositoryInterface userDetailsRepositoryInterface;

	@Override
	public boolean addNewUser(UserDetails user) {
		return userDetailsRepositoryInterface.addNewUser(user);
	}

	@Override
	public boolean updateUserDetails(UserDetails user) {
		return userDetailsRepositoryInterface.updateUserDetails(user);
	}

	@Override
	public boolean userLogin(int userId, String password) {
		UserDetails userDetails = userDetailsRepositoryInterface.findUser(userId);
		if(password.equals(userDetails.getPassword()))
			return true;
		return false;
	}

	@Override
	public UserDetails getUserById(int userId) {
		return userDetailsRepositoryInterface.findUser(userId);
		
	}

	@Override
	public List<UserDetails> getAllUsers() {
		return userDetailsRepositoryInterface.getAllUsers();
	}

	@Override
	public boolean updatePassword(int userId, String oldPassword, String newPassword) {
		if(oldPassword == newPassword ) 
			return false;
		if(userDetailsRepositoryInterface.findUser(userId).getPassword().equals(oldPassword))
			return userDetailsRepositoryInterface.updatePassword(userId, newPassword);
		return false;
	}

}
