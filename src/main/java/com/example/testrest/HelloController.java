package com.example.testrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/users")
    public String getUsers(){
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/home/users/8", String.class);
        return response.getBody() + " \n" + response.getStatusCode() + " \n" + response.getHeaders() + " \n" + response.getStatusCodeValue();

    }

    @PostMapping("/users")
    public String  postUsers(@RequestBody Users users){
        HttpEntity<Users> request = new HttpEntity<>(users);
        return restTemplate.postForObject("http://localhost:8080/home/users", request, String.class);
//        return restTemplate.postForObject("http://localhost:8080/home/users", request, String.class);
    }

    @DeleteMapping("/users/{id}")
    public void   deleteUser(@PathVariable int id){
        restTemplate.delete("http://localhost:8080/home/users/{id}", id);
    }


    @PutMapping("/users/{id}")
    public void UpdateUsers(@RequestBody Users users, @PathVariable int id){
        HttpEntity<Users> request = new HttpEntity<>(users);
        // Put Method Return Void
         restTemplate.put("http://localhost:8080/home/users/{id}", request, id);
    }


}
