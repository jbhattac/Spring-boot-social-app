package com.jbook.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

@Service
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	private static int usersCount = 3;

	static {
		users.add(new User(1, "Adam", new Date(), RandomString.make()));
		users.add(new User(2, "Eve", new Date(), RandomString.make()));
		users.add(new User(3, "Jack", new Date(), RandomString.make()));
	}
	
	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
			user.setPassword(RandomString.make());
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		return users.stream()
			.filter(u->id == u.getId())
			.findAny().get();
	}

	public void deleteById(int id) {
		users = users.stream()
				.filter(user->user.getId() == id)
				.collect(Collectors.toList());
	}
		
}
