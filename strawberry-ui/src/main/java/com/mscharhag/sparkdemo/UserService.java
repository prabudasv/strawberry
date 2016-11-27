package com.mscharhag.sparkdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

	private Map<String, User> users = new HashMap<>();

	public List<User> getAllUsers() {
		return new ArrayList<>(users.values());
	}

	public User getUser(String id) {
		return users.get(id);
	}

	public User createUser(String name, String email) {
		System.out.println("Got request for createing a new user...");
		failIfInvalid(name, email);
		User user = new User(name, email);
		users.put(user.getId(), user);
		System.out.println("User ID is " + user.getId());
		System.out.println( "Size of user array is " + users.size());
		return user;
	}

	public User updateUser(String id, String name, String email) {
		System.out.println( "Got request for updating user ... ");
		System.out.println( "Size of user array is " + users.size());
		User user = users.get(id);
		System.out.println( "Size of user array is " + getAllUsers().size());
		if (user == null) {
			throw new IllegalArgumentException("No user with id '" + id + "' found");
		}
		failIfInvalid(name, email);
		user.setName(name);
		user.setEmail(email);

		return user;
	}

	public void deleteUser(String id, String name, String email) {
		System.out.println( "Got request for deleting user ... ");
		System.out.println( "Size of user array is " + users.size());
		User user = users.get(id);
		users.remove(id);
		System.out.println( "Size of user array is " + getAllUsers().size());
		if (user == null) {
			throw new IllegalArgumentException("No user with id '" + id + "' found");
		}
		failIfInvalid(name, email);


	}

	private void failIfInvalid(String name, String email) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Parameter 'name' cannot be empty");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Parameter 'email' cannot be empty");
		}
	}
}
