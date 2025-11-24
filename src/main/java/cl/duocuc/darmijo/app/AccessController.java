package cl.duocuc.darmijo.app;

import cl.duocuc.darmijo.core.exceptions.AuthorityException;
import cl.duocuc.darmijo.users.models.User;
import cl.duocuc.darmijo.users.service.JwtService;
import cl.duocuc.darmijo.users.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping
public class AccessController {
    @Resource
    private UserService userService;
    @Resource
    private JwtService jwtService;
    
    
    
    @GetMapping("/login")
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView("login.html");
        modelAndView.addObject("title", "Darmijo");
        return modelAndView ;
    }
    
    @PostMapping("/login")
    public String login(
        HttpServletResponse response,
        @RequestParam String email,
        @RequestParam String password
    ) throws AuthorityException {
        log.info("Authenticating user: {}", email);
        User user = userService.authenticateUser(email.translateEscapes(), password.translateEscapes()); // o
        log.info("User authenticated: {}", user);
        String token = jwtService.createWithSubject(user.getEmail());
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        log.info("token created: {}", token);
        return "redirect:/recipes";
    }
    
    @GetMapping("/logout")
    public ModelAndView logout(
        HttpServletResponse response
    ) {
        log.info("Logging out");
        Cookie cookie = new Cookie("Authorization", "null");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return new ModelAndView("redirect:/");
    }
}
