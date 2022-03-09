package com.fundamentos.springboot.fundamentos.UsesCase;

import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {

    private UserService userService;

    public DeleteUser(UserService userService){
        this.userService = userService;
    }

    public void remove(long id) {
        userService.delete(id);
    }
}
