package org.nnn.hr.controller;

import java.util.Optional;
import java.util.Set;

import org.nnn.hr.manager.HrManager;
import org.nnn.hr.repository.Permission;
import org.nnn.hr.repository.User;
import org.nnn.hr.repository.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HrController {

	@Autowired
	HrManager hrManager;
	
	@GetMapping("/users")
	public Iterable<User> getAllUsers() {
		return hrManager.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable(value = "id") String id) {
		return hrManager.getUserById(id);
	}
	
	@GetMapping("/users/titles/{title}")
	public Optional<User> getUserByTitle(@PathVariable(value = "title") String title) {
		return hrManager.getUserByTitle(title);
	}
	
	@PostMapping("/users/insert")
	public User insertUser(@RequestBody User user) {
		return hrManager.insertUser(user);
	}
	
	@GetMapping("/permissions")
	public Iterable<Permission> getAllPermissions() {
		return hrManager.getAllPermissions();
	}
	
	@GetMapping("/users/permissions")
	public Iterable<UserPermission> getAllUsersPermissions() {
		return hrManager.getAllUsersPermissions();
	}
	
	@GetMapping("/users/{id}/permissions")
	public Set<Permission> getUserPermissions(@PathVariable(value = "id") String userId) {
		return hrManager.getUserPermissions(userId);
	}
	
	@GetMapping("/users/native/{id}")
	public Iterable<User> getUserNativeQuery(@PathVariable(value = "id") String userId) {
		return hrManager.getUserNativeQuery(userId);
	}
	
	@GetMapping("/users/native/{name}/update/{title}")
	public int getUserNativeQuery(@PathVariable(value = "name") String name, @PathVariable(value = "title") String title) {
		return hrManager.updateUserTitleByNameNativeQuery(name, title);
	}
	
	@GetMapping("/users/counter")
	public Object getCounterWithEntityManager() {
		return hrManager.getCounterWithEntityManager();
	}
	
}
