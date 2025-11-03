package cl.duocuc.darmijo.users.controllers;

import cl.duocuc.darmijo.core.exceptions.AuthorityException;
import cl.duocuc.darmijo.core.exceptions.ResourceNotFoundException;
import cl.duocuc.darmijo.users.models.AuthenticateUserParams;
import cl.duocuc.darmijo.users.models.CreateUserParams;
import cl.duocuc.darmijo.users.models.UpdateUserParams;
import cl.duocuc.darmijo.users.models.User;
import cl.duocuc.darmijo.users.service.JwtService;
import cl.duocuc.darmijo.users.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Resource
    private UserService userService;
    @Resource
    private JwtService jwtService;
    
    @PostConstruct
    public void init() {
        String password = "p4ssw0rD!";
        List.of(
            new CreateUserParams("jp@localhost", "Juan Perez", password),
            new CreateUserParams("da@localhost", "Diego Armijo", password),
            new CreateUserParams("hs@localhost", "Hari Seldon", password),
            new CreateUserParams("gd@localhost", "Gaal Dornick", password),
            new CreateUserParams("mh@localhost", "Hover Mallow", password)
        ).forEach(this::postUser);
    }
    
    @PostMapping
    public ResponseEntity<?> postUser(
        CreateUserParams params
    ) {
        Optional<User> user = userService.createUser(
            params.getEmail(),
            params.getDisplayName(),
            "",
            params.getPassword()
        );
        return ResponseEntity.ok(user);
    }
    
    
    
    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{ulid}")
    public ResponseEntity<?> getUser(
        @PathVariable String ulid
    ) {
        Optional<User> user = userService.getUserByUlid(ulid);
        return ResponseEntity.ok(user);
    }
    
    @PatchMapping
    public ResponseEntity<?> updateUser(
        UpdateUserParams params
    ) throws AuthorityException {
        Optional<User> user = userService.updateUser(
            params.getEmail(),
            params.getDisplayName(),
            params.getPassword(),
            params.getNewPassword()
        );
        return ResponseEntity.ok(user);
    }
    
    @DeleteMapping("/{ulid}")
    public ResponseEntity<?> deleteUser(
        @PathVariable String ulid
    ) throws ResourceNotFoundException {
        userService.deleteUserByUlid(ulid);
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/auth")
    public ResponseEntity<?> authenticateUser(
        AuthenticateUserParams params
    ) throws AuthorityException {
        User user = userService.authenticateUser(params.getEmail(), params.getPassword());
        return ResponseEntity.ok(user);
    }
    
}
