package com.wjnnovoa.app.controller;

import com.wjnnovoa.app.entity.User;
import com.wjnnovoa.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    //Consultar un usuario por id
    @GetMapping("/{id-user}")
    public ResponseEntity<?> readUser(@PathVariable(value = "id-user") Long id){
        Optional<User> oUser = userService.findById(id);

        if(oUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oUser);
    }

    //Actualizar un usuario
    @PutMapping("/{id-user}")
    public ResponseEntity<?> updateUser(@RequestBody User userDetails, @PathVariable(value = "id-user") Long userId){
        Optional<User> user = userService.findById(userId);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        user.get().setEmail(userDetails.getEmail());
        user.get().setName(userDetails.getName());
        user.get().setSurname(userDetails.getSurname());
        user.get().setEnable(userDetails.getEnable());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));

    }

    //Eliminar un usuario
    @DeleteMapping("/{id-user}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id-user") Long userId){
        if(userService.findById(userId).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<User> readUserAll(){
        return StreamSupport
                .stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
