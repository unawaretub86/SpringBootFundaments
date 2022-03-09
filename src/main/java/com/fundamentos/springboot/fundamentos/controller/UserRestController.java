package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.UsesCase.CreateUser;
import com.fundamentos.springboot.fundamentos.UsesCase.DeleteUser;
import com.fundamentos.springboot.fundamentos.UsesCase.GetUser;
import com.fundamentos.springboot.fundamentos.UsesCase.UpdateUser;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this makes format json to data
@RestController
//enrouting
@RequestMapping("/api/users")
public class UserRestController {

    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;

    public UserRestController(
            GetUser getUser,
            CreateUser createUser,
            DeleteUser deleteUser,
            UpdateUser UpdateUser
    ){
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = UpdateUser;
    }

//    get
    @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }

//    create - post
//    esto es una buena practica para el front en el http

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser) {
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

//    delete
    //respondemos que fue creadp pero no hay nada creado
    @DeleteMapping("/{id}")
    ResponseEntity deteUser(@PathVariable long id){
        deleteUser.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    update
//    put
    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
    }
}
