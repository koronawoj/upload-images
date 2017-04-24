package pl.koronawoj.service;

import java.util.List;

import pl.koronawoj.model.User;

public interface UserService {
	List<User> findAllUsers();
	
	User findByUserName(String userName);
	
	User save(User user);
}
