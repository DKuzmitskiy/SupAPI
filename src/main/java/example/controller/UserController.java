package example.controller;

import example.model.User;
import example.service.UserService;
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

//    @RequestMapping(value = "api/list", method = RequestMethod.GET)
    // !!! @ResponseBody is not need for @RestController
    @GetMapping("list")
    public List getAllUsers(){
        return userService.getAllUsers();
    }

//    @RequestMapping(value = "api/add", method = RequestMethod.POST)
    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<User>(userService.add(user), headers, HttpStatus.CREATED);
    }

//    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody User addUser(@RequestBody User user){
//        return userService.add(user);
//    }

//    @RequestMapping(value = "api/getById/{id}", method = RequestMethod.GET)
//    public @ResponseBody User getById(@PathVariable("id") int id ){
//        return userService.getById(id);
//    }

//    //params = {"id"} - is optional
//    @RequestMapping(value = "api/getById", params = {"id"}, method = RequestMethod.GET)
//    public @ResponseBody User getById(@RequestParam("id") int id ){
//        return userService.getById(id);
//    }

    @GetMapping("getById") // Spring 4.3
    public User getById(@RequestParam("id") int id ){
        return userService.getById(id);
    }

//    @RequestMapping(value = "api/update", method = {RequestMethod.POST, RequestMethod.PUT})
    @PostMapping("update")
    public User update(@RequestBody User user){
        return userService.update(user);
    }

//    @PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> update(@RequestBody User user){
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json; charset=utf-8");
//        return new ResponseEntity<User>(userService.update(user), headers, HttpStatus.OK);
//    }

//    @RequestMapping(value = "api/delete/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("delete/{id}")
    public User delete(@PathVariable("id") int id){
        return userService.delete(id);
    }
}
