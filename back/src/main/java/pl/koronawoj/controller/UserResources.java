package pl.koronawoj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.koronawoj.model.User;
import pl.koronawoj.service.UserService;

@RestController
@RequestMapping("/rest")
public class UserResources {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/users")
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}
	
	@RequestMapping(value = "/user/userName", method = RequestMethod.POST)
	public User findByUserName(@RequestBody String userName) {
		return userService.findByUserName(userName);
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user) {
		return userService.save(user);
	}
	
}
