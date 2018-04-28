package sup.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import sup.model.User;
import sup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/")
public class UserController {

    @Autowired
    private UserService userService;

    private HttpHeaders headers = new HttpHeaders();

    public UserController() {
        headers.add("Content-Type", "application/json; charset=utf-8");
    }

    @GetMapping("list")
    public ResponseEntity<List> getAllUsers(){
        return new ResponseEntity<>( (List) userService.getAllUsers(), headers, HttpStatus.OK);
    }


    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.add(user), headers, HttpStatus.CREATED);
    }

    @GetMapping("getById")
    public ResponseEntity<User> getById(@RequestParam("id") Long id ){
        return new ResponseEntity<>(userService.getById(id), headers, HttpStatus.OK);
    }

    @PostMapping("update")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<User> update(@RequestBody User user){
        return new ResponseEntity<>(userService.update(user), headers, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<User> delete(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.delete(id), headers, HttpStatus.OK);
    }
}
