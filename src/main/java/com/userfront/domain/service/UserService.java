package com.userfront.domain.service;

import java.util.List;
import java.util.Set;

import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;

public interface UserService {
	
	User findByUsername(String username);
	User findByEmail(String email);
	
	boolean checkUserExists(String username, String email);
	
	boolean checkUsernameExists(String username);
	
	boolean checkEmailExists(String email);
	
	void Save(User user);
	
	User saveUser(User user);
	
	List<User> findUserList();
	
	void enableUser(String username);
	
	void disableUser(String username);	
	
	void SaveUser(User user);
	
	User createUser(User user, Set<UserRole> userRoles);
}
