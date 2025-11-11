package cl.duocuc.darmijo.users.service;

import cl.duocuc.darmijo.core.exceptions.AuthorityException;
import cl.duocuc.darmijo.core.exceptions.ResourceNotFoundException;
import cl.duocuc.darmijo.users.models.User;
import cl.duocuc.darmijo.users.repository.UserRepository;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    public Optional<User> createUser(String email, String displayName, String roles, String password) {
        String ulid = UlidCreator.getUlid().toString();
        User user = new User();
        user.setUlid(ulid);
        user.setEmail(email);
        user.setDisplayName(displayName);
        user.setRoles(roles);
        user.setHashedPassword(hashPassword(password));
        userRepository.save(user);
        return userRepository.findByUlid(ulid);
    }
    
    public Optional<User> updateUser(String email, String displayName, String password, String newPassword) throws AuthorityException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new AuthorityException("Invalid email or password");
        }
        User user = optionalUser.get();
        boolean passwordMatches = checkPassword(password, user.getHashedPassword());
        if(!passwordMatches) {
            throw new AuthorityException("Invalid email or password");
        }
        user.setEmail(email);
        user.setDisplayName(displayName);
        user.setHashedPassword(hashPassword(newPassword));
        userRepository.save(user);
        return getUserByUlid(user.getUlid());
        
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserByUlid(String ulid) {
        return userRepository.findByUlid(ulid);
    }
    
    public void deleteUserByUlid(String uid) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findByUlid(uid);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.delete(user.get());
        
    }
    
    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    
    public User getUserByEmail(String email) throws ResourceNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        return optionalUser.get();
    }
    
    public User authenticateUser(String email, String password) throws AuthorityException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new AuthorityException("Invalid email or password");
        }
        
        User user = optionalUser.get();
        boolean passwordMatches = checkPassword(password, user.getHashedPassword());
        if(!passwordMatches) {
            throw new AuthorityException("Invalid email or password");
        }
        return user;
    }
    
    private boolean checkPassword(String rawPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
    
}
