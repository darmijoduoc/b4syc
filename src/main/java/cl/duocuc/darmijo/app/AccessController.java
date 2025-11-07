package cl.duocuc.darmijo.app;

import cl.duocuc.darmijo.core.exceptions.AuthorityException;
import cl.duocuc.darmijo.users.models.User;
import cl.duocuc.darmijo.users.service.JwtService;
import cl.duocuc.darmijo.users.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("login.html");
        modelAndView.addObject("title", "Darmijo");
        return modelAndView ;
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login Page");
        return "login.html";
    }
    
    @PostMapping("/login")
    public String postLogin(
        HttpServletResponse response,
        @RequestParam String email,
        @RequestParam String password
    ) throws AuthorityException {
        log.info("Authenticating user: {}", email);
        User user = userService.authenticateUser(email, password);
        log.info("User authenticated: {}", user);
        String token = jwtService.createWithSubject(user.getEmail());
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        return "redirect:/app/dashboard";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "logout.html";
    }
}
