package com.klef.fsad.sdp.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.klef.fsad.sdp.service.UserService;

@Component
public class JwtFilter extends OncePerRequestFilter 
{
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException 
    {
        String path = request.getServletPath();

        // Skip authentication for auth endpoints and Swagger
        if (path.startsWith("/auth") ||
            path.startsWith("/swagger-ui") ||
            path.startsWith("/v3/api-docs") ||
            path.equals("/swagger-ui.html") ||
            path.equals("/customerapi/registration")) 
        {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        System.out.println(header);

        if (header == null || !header.startsWith("Bearer ")) 
        {
            // No token or wrong format → Send clear message
            sendErrorResponse(response, 401, "Authorization header is missing or must start with 'Bearer '");
            return;
        }

        String token = header.substring(7).trim();

        try 
        {
            String username = jwtUtil.extractUsername(token);

            if (username == null) 
            {
                sendErrorResponse(response, 401, "Invalid token: Username could not be extracted");
                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) 
            {
                UserDetails userDetails = service.loadUserByUsername(username);

                if (userDetails != null && jwtUtil.validateToken(token, userDetails)) 
                {
                    // Valid token → Set authentication
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } 
                else 
                {
                    // Token exists but validation failed (expired, tampered, wrong signature, etc.)
                    sendErrorResponse(response, 401, "Invalid or expired token");
                    return;
                }
            }
        } 
        catch (Exception e) 
        {
            // Catch any JWT parsing / validation errors
            sendErrorResponse(response, 401, "Invalid token: " + e.getMessage());
            return;
        }

        // Continue with the filter chain if everything is fine
        chain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException 
    {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String error = (status == 401) ? "Unauthorized" : "Forbidden";

        String jsonResponse = "{\"error\":\"" + error + "\",\"message\":\"" + message + "\"}";

        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
}