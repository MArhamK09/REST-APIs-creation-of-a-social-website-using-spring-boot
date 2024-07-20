package com.restapi.learnrestapispring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Azaz Khan");
	}
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Azaz","Khan"));
	}
	
	@GetMapping(path="/person",params="version=1")
	public PersonV1 getFirstVersionOfPersonByRequestParam() {
		return new PersonV1("Azaz Khan1");
	}
	@GetMapping(path="/person",params="version=2")
	public PersonV2 getSecondVersionOfPersonByRequestParams() {
		return new PersonV2(new Name("Azaz","Khan1"));
	}
	
	@GetMapping(path="/person/header",headers="X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonByHeader() {
		return new PersonV1("Azaz Khan2");
	}
	
	@GetMapping(path="/person/header",headers="X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonByHeader() {
		return new PersonV2(new Name("Azaz","Khan2"));
	}
	
	@GetMapping(path="/person/accept",produces="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonByMediaType() {
		return new PersonV1("Azaz Khan3");
	}
	
	@GetMapping(path="/person/accept",produces="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonByMediaType() {
		return new PersonV2(new Name("Azaz","Khan3"));
	}
	
}
