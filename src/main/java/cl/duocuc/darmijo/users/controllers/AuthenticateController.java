package cl.duocuc.darmijo.users.controllers;

import cl.duocuc.darmijo.core.exceptions.AuthorityException;
import cl.duocuc.darmijo.users.models.AuthenticateUserParams;
import cl.duocuc.darmijo.users.models.User;
import cl.duocuc.darmijo.users.service.JwtService;
import cl.duocuc.darmijo.users.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("access")
public class AuthenticateController {
    @Resource
    private UserService userService;
    @Resource
    private JwtService jwtService;
    
    
    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateUserParams params) throws AuthorityException {
        log.info("Authenticating user: {} {}", params.getEmail(), params.getPassword());
        
        User user = userService.authenticateUser(
            params.getEmail(),
            params.getPassword()
        );
        
        log.info("Authenticated user: {} {}", params.getEmail(), params.getPassword());
        
        String jwt = jwtService.createWithSubject(user.getEmail());
        
        Map<String, Object> response = Map.ofEntries(
            Map.entry("token", jwt),
            Map.entry("user", user)
        );
        
        return ResponseEntity.ok(response);
    }
}
