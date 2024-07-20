package com.restapi.learnrestapispring;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity(name="user_details")
public class User {
	protected User() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=4)
	@NotNull
	@Pattern(regexp="^[a-zA-Z ]*$")
	//@JsonProperty("user_name")
	private String name;
	
	@NotNull
	@Past
	//@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	@NotNull
	@Pattern(regexp="^(?:male|Male|MALE|female|Female|FEMALE|other|Other|OTHER)$")
	private String gender;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	public User(Integer id, String name, LocalDate birthDate, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + "]";
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	

}
