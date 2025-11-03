package cl.duocuc.darmijo.core.filters;

import cl.duocuc.darmijo.users.service.JwtService;
import io.jsonwebtoken.*;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class CustomJwtAuthorizationFilter extends OncePerRequestFilter {
    
    @Resource
    private JwtService jwtService;
    
/*    public CustomJwtAuthorizationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }*/
    
    private void setAuthentication(Claims claims) {
        //List<String> authorities = (List<String>) claims.get("authorities");
        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
        SecurityContextHolder.getContext().setAuthentication(auth);
        
    }
    
    private Optional<String> getToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader("Authorization");
        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer")) return Optional.empty();
        
        String unverifiedToken = request
            .getHeader("Authorization")
            .replace("Bearer", "")
            .trim();
        
        if (unverifiedToken.isEmpty()) return Optional.empty();
        
        return Optional.of(unverifiedToken);
    }
    
     //por aqui pasan todas las solicitudes, si todas
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        
        log.info("CustomJwtAuthorizationFilter doFilter {}", request.getRequestURI());
        try {
            //aqui se saltan las rutas de acceso publico, access/auth, access/register, acess/refresh, etc
            if(request.getRequestURI().startsWith("/access/")) {
                log.info("Skipping JWT check for /access/ endpoint");
                filterChain.doFilter(request, response);
                return;
            }
            Optional<String> optionalUnverifiedToken = getToken(request);
            if(optionalUnverifiedToken.isEmpty()) {
                throw new MalformedJwtException("Invalid JWT token");
            }
            String unverifiedToken = optionalUnverifiedToken.get();
            Claims claims = jwtService.verifyAndGetClaims(unverifiedToken);
            log.info("Valid token found!");
            setAuthentication(claims);
            filterChain.doFilter(request, response);
            
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            log.error(e.getMessage());
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }
    }

}
