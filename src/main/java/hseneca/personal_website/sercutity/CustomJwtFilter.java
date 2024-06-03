package hseneca.personal_website.sercutity;

import hseneca.personal_website.utils.JwtUtilsHelper;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.ArrayList;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtilsHelper jwtUtilsHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
//        System.out.println("Hellooo Filter");
        try {
            String jwt = getTokenFromHeader(request);
            boolean isSuccess = jwtUtilsHelper.verifyToken(jwt);
            if (isSuccess){
//                String data = jwtUtilsHelper.getDataFromToken(jwt);
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken("",
                                "", new ArrayList<>());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(token);
            }
//            System.out.println("Hello filter:  " + isSuccess);

        } catch (Exception e){
//            System.err.println("Lỗi nhận token - " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
    private String getTokenFromHeader(HttpServletRequest request){
        String header  = request.getHeader("Authorization");
//        System.out.println( "kiem tra" + header);
        String token = null;
        if(StringUtils.hasText(header) && header.startsWith("Bearer ")){
            token = header.substring(7);

        }
        return token;

    }
}
