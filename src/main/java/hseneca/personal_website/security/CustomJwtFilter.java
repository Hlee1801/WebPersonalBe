package hseneca.personal_website.security;

import hseneca.personal_website.entity.User;
import hseneca.personal_website.utils.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenProvider jwtUtilsHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
//        System.out.println("Hellooo Filter");
        try {
            String jwt = getTokenFromHeader(request);
            boolean isSuccess = jwtUtilsHelper.validateToken(jwt);
            if (isSuccess) {
//                String data = jwtUtilsHelper.getDataFromToken(jwt);
                Long userId = jwtUtilsHelper.getUserIdFromJWT(jwt);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        new CustomUserDetail(User.builder().id(userId).build()),
                        null, List.of());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
//            System.out.println("Hello filter:  " + isSuccess);

        } catch (Exception e) {
            System.err.println("Lỗi nhận token - " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
//        System.out.println( "kiem tra" + header);
        String token = null;
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }
        return token;

    }

}
