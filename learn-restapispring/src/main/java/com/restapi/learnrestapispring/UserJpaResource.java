package com.restapi.learnrestapispring;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController

public class UserJpaResource {
	private UserRepository repo; 
	private PostRepository repoPost;

	public UserJpaResource(PostRepository repoPost, UserRepository repo) {
		this.repoPost = repoPost;
		this.repo = repo;
	}

	@GetMapping("/jpa/users")
	public List<User> repoAllUsers() {
		return repo.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<Optional<User>> repoOneUser(@PathVariable int id) {
		Optional<User> user = repo.findById(id);

		if (user == null)
			throw new UserNotFoundException("id: " + id);
		EntityModel<Optional<User>> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).repoAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repo.deleteById(id);

	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable int id) {
		Optional<User> user = repo.findById(id);

		if (user == null)
			throw new UserNotFoundException("id: " + id);

		return user.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = repo.findById(id);

		if (user == null)
			throw new UserNotFoundException("id: " + id);
		post.setUser(user.get());
		Post newPost = repoPost.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/jpa/users")
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		User savedUser = repo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	 
}
