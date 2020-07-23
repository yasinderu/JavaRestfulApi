package com.restful.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    Map<String, UserRest> users;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<UserRest> createUser(@RequestBody UserDetailRequest userDetail) {
        UserRest returnVal = new UserRest();
        returnVal.setFirstName(userDetail.getFirstName());
        returnVal.setLastName(userDetail.getLastName());
        returnVal.setEmail(userDetail.getEmail());

        String userId = UUID.randomUUID().toString();
        returnVal.setUsserId(userId);

        if (users == null)
            users = new HashMap<>();
        users.put(userId, returnVal);

        return new ResponseEntity<UserRest>(returnVal, HttpStatus.OK);
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        users.remove(id);
        return ResponseEntity.noContent().build();
    }
}