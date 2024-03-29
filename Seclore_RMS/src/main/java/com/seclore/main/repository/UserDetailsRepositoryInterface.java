package com.seclore.main.repository;

import java.util.List;

import com.seclore.main.domain.UserDetails;

public interface UserDetailsRepositoryInterface {
	public boolean addNewUser(UserDetails user);

	public boolean updateUserDetails(UserDetails user);


	public UserDetails getUserById(int userId);

	public List<UserDetails> getAllUsers();

	public boolean updatePassword(int userId, String password);
	public boolean updateActive(int userId, boolean isActive);
}
