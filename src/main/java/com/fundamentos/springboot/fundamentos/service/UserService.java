package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
//       esto nos permite mostrar en pantalla lo que vamos registrando
            .peek(user -> LOG.info("User Inserted: " +user))
                .forEach(user -> userRepository.save(user));
//        .forEach(userRepository::save);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
