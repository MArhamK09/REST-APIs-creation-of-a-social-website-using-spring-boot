package com.restapi.learnrestapispring;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users= new ArrayList<>();
	private static int usersCount=0;	
	static {
		users.add(new User(++usersCount,"Adam",LocalDate.now().minusYears(30),"Male"));
		users.add(new User(++usersCount,"Caleb",LocalDate.now().minusYears(25),"Male"));
		users.add(new User(++usersCount,"John",LocalDate.now().minusYears(20),"Male"));
		users.add(new User(++usersCount,"Alex",LocalDate.now().minusYears(47),"Male"));
		users.add(new User(++usersCount,"Kathy",LocalDate.now().minusYears(19),"Female"));
		users.add(new User(++usersCount,"Philip",LocalDate.now().minusYears(23),"Male"));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		Predicate<? super User> predicate=user->user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	public User deleteById(int id) {
		Predicate<? super User> predicate=user->user.getId().equals(id);
		users.removeIf(predicate);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
}
