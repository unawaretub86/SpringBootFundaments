package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.UsesCase.GetUser;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//this makes format json to data
@RestController
//enrouting
@RequestMapping("/api/users")
public class UserRestController {
//    create

    private GetUser getUser;

    public UserRestController(GetUser getUser){
        this.getUser = getUser;
    }
    @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }


//    get

//    delete

//    update
}
