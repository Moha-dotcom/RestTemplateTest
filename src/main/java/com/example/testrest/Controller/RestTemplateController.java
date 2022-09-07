package com.example.testrest.Controller;

import com.example.testrest.Model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/users")
    public String getUsers(){

//        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/home/users").build().toUri();
//        Sends an HTTP GET request, returning a ResponseEntity containing an object mapped from the response body
        ResponseEntity<String> response =  restTemplate.getForEntity("http://localhost:8080/users", String.class);
        logger.info("Fetch : " + response.getBody() + " " + response.getStatusCode() + " " + response.getHeaders().getDate());
        return response.getBody();
//        return   restTemplate.getForObject("http://localhost:8080/home/users", String.class);
    }

    @PostMapping("/users")
    public String  postUsers(@RequestBody Users users){
        HttpEntity<Users> request = new HttpEntity<>(users);
        return restTemplate.postForObject("http://localhost:8080/users", request, String.class);
    }


    @PutMapping("/users/{id}")
    public void UpdateUsers(@RequestBody Users users, @PathVariable int id){
        HttpEntity<Users> request = new HttpEntity<>(users);
//        restTemplate.put("", Users.class, id);
        // Put Method Return Void
         restTemplate.put("http://localhost:8080/users/{id}", request, id);
    }

    @DeleteMapping("/users/{id}")
    public void   deleteUser(@PathVariable int id){

        restTemplate.delete("http://localhost:8080/users/{id}", id);
    }


}
