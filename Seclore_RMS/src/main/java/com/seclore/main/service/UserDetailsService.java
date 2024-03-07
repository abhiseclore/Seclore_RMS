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
		user.setPassword("Seclore123");
		user.setIsActive(true);
		return userDetailsRepositoryInterface.addNewUser(user);
	}

	@Override
	public boolean updateUserDetails(UserDetails user) {
		return userDetailsRepositoryInterface.updateUserDetails(user);
	}

	@Override
	public UserDetails userLogin(int userId, String password) {
		UserDetails userDetails = userDetailsRepositoryInterface.getUserById(userId);
		if(password.equals(userDetails.getPassword()))
			return userDetails;
		return null;
	}

	@Override
	public UserDetails getUserById(int userId) {
		return userDetailsRepositoryInterface.getUserById(userId);
		
	}

	@Override
	public List<UserDetails> getAllUsers() {
		return userDetailsRepositoryInterface.getAllUsers();
	}

	@Override
	public boolean updatePassword(int userId, String oldPassword, String newPassword) {
		if(userDetailsRepositoryInterface.getUserById(userId).getPassword().equals(oldPassword))
			return userDetailsRepositoryInterface.updatePassword(userId, newPassword);
		return false;
	}

	@Override
	public boolean updateActive(int userId, boolean isActive) {
		// TODO Auto-generated method stub
		return userDetailsRepositoryInterface.updateActive(userId, isActive);
	}
	

}
