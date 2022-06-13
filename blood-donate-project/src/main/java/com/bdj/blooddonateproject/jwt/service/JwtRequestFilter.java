package com.bdj.blooddonateproject.jwt.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdj.blooddonateproject.config.UserDetailServiceImp;
import com.bdj.blooddonateproject.config.UserPrincipal;
import com.bdj.blooddonateproject.jwt.model.JwtUtil;
import com.bdj.blooddonateproject.jwt.model.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

// OncePerRequestFilter sẽ chỉ thực hiện một lần filter trong mỗi request.
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUtil jwtUtil;

    public JwtRequestFilter() {
    }

    public JwtRequestFilter(TokenService tokenService, JwtUtil jwtUtil) {
        this.tokenService = tokenService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String authorizationHeader = request.getHeader("Authorization");
        UserPrincipal user = null;
        Token token = null;
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Token ")) {
            String jwt = authorizationHeader.substring(6);
            token = tokenService.findByToken(jwt);
            if (token != null) {
                user = jwtUtil.getUserFromToken(jwt);
            } else {
                response.setStatus(404);
                return;
            }
        }

        if (null != user && null != token && token.getTokenExpDate().after(new Date())) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            user.getAuthorities().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toString())));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                    authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

}
