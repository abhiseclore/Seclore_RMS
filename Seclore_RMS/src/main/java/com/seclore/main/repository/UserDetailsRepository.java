package com.seclore.main.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.seclore.main.domain.UserDetails;

@Repository
public class UserDetailsRepository implements UserDetailsRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static String ADD_NEW_USER = "insert into user_details(name,password, position, is_active) values(?,?,?,?)";
	private static String UPDATE_USER_DETAILS = "UPDATE user_details set name =?,password=? , position=? , is_active=? where user_id=?";
	private static String SELECT_SINGLE_USER = "select * from user_details where user_id = ?";
	private static String UPDATE_PASSWORD = "UPDATE user_details set password=? where user_id=?";
	private static String SELECT_ALL_USERS = "select * from user_details";

	@Override
	public boolean addNewUser(UserDetails user) {
		Object[] args = { user.getName(), user.getPassword(), user.getPosition(), true };
		int rows = jdbcTemplate.update(ADD_NEW_USER, args);
		if (rows > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateUserDetails(UserDetails user) {
		Object[] args = { user.getName(), user.getPassword(), user.getPosition(), user.isActive(), user.getUserId() };
		int rows = jdbcTemplate.update(UPDATE_USER_DETAILS, args);
		if (rows > 0)
			return true;
		return false;
	}

	@Override
	public UserDetails findUser(int userId) {
		UserDetailsRowMapper userDetailsRowMapper = new UserDetailsRowMapper();
		try {
			UserDetails userDetails = jdbcTemplate.queryForObject(SELECT_SINGLE_USER, userDetailsRowMapper, userId);
			return userDetails;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<UserDetails> getAllUsers() {
		List< UserDetails> usersList = jdbcTemplate.query(SELECT_ALL_USERS, new UserDetailsRowMapper());
		return usersList;
	}

	@Override
	public boolean updatePassword(int userId, String password) {
		Object[] args = {password, userId};
		
		int rows = jdbcTemplate.update(UPDATE_PASSWORD, args);
		if (rows > 0)
			return true;
		return false;	
	}

}
