package com.seclore.main.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.seclore.main.domain.UserDetails;
@Repository
public class UserDetailsRepository implements UserDetailsRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate ;
	private static String ADD_NEW_USER="insert into user_details(name,password, position, is_active) values(?,?,?,?)";
	
	@Override
	public boolean addNewUser(UserDetails user) {
		Object[] args = {user.getName()};
		return false;
	}

	@Override
	public boolean updateUserDetails(UserDetails user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userLogin(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDetails findUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDetails> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePassword(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
