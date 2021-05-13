package app.controller;

import app.domain.User;
import app.dto.UserDTO;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping(value="/get/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO>
    getUser(@PathVariable String email) {
        User u = service.findOneByEmail(email);
        UserDTO toRet = new UserDTO(u);
        return new ResponseEntity<>(toRet, HttpStatus.OK);
    }

    @PutMapping(value = "/edit/{email}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO>
    editUser(@PathVariable String email, @RequestBody UserDTO editedUser) {
        User user = service.findOneByEmail(email);
        service.save(user, editedUser);
        return new ResponseEntity<>(editedUser, HttpStatus.OK);
    }
}
