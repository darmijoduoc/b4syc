package cl.duocuc.darmijo.app;

import cl.duocuc.darmijo.users.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("app")
public class AppController {
    
    @Resource
    private JwtService jwtService;
    
    @GetMapping("dashboard")
    public ModelAndView getDashboard(@CookieValue String token) {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        log.info("get dashboard {}", token);
        Claims claims = jwtService.verifyAndGetClaims(token);
        String city = (String) claims.get("city");
        modelAndView.addObject("city", city);
        return modelAndView;
    }
    
}
