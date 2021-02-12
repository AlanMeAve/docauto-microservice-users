package com.sysview.docauto.microservice.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sysview.docauto.microservice.users.model.User;
import com.sysview.docauto.microservice.users.service.UserService;

@RestController
public class UserController {
	
	@Autowired UserService userService;

	@Value("${eureka.instance.instance-id}") 
	private String port;
	
	
	@GetMapping("/getIntance")
	public ResponseEntity<Object> getIntance(){
		try {
			return ResponseEntity.ok("port: ".concat(port));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll(){
		try {
			return ResponseEntity.ok(userService.getAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/save")
	public ResponseEntity<Object> save(@RequestBody User user){
		try {
			return ResponseEntity.ok(userService.save(user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/update/{username}")
	public ResponseEntity<Object> update(@PathVariable String username, @RequestBody User user){
		try {
			return ResponseEntity.ok(userService.update(username, user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/findByUsername/{username}")
	public ResponseEntity<Object> findByUsername(@PathVariable String username){
		try {
			return ResponseEntity.ok(userService.findByUsername(username));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{username}")
	public ResponseEntity<Object> delete(@PathVariable String username){
		try {
			return ResponseEntity.ok(userService.delete(username));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
