package com.summar.member.filter;

import com.summar.member.common.SummarErrorCode;
import com.summar.member.common.SummarJwtException;
import com.summar.member.service.CustomUserDetailService;
import com.summar.member.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (IllegalArgumentException e) {
                logger.error("error occured during getting username from token!", e);
                throw new SummarJwtException(SummarErrorCode.INVALID_TOKEN.getCode(), SummarErrorCode.INVALID_TOKEN.getMessage());
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore!", e);
                throw new SummarJwtException(SummarErrorCode.EXPIRED_TOKEN.getCode(), SummarErrorCode.EXPIRED_TOKEN.getMessage(), e);
            } catch (SignatureException e) {
                logger.error("Authentication Failed. Username or Password not valid.");
                throw new SummarJwtException(SummarErrorCode.AUTHENTICATION_FAILED.getCode(), SummarErrorCode.AUTHENTICATION_FAILED.getMessage(), e);
            } catch (MalformedJwtException e) {
                logger.error("the token is not valid!", e);
                throw new SummarJwtException(SummarErrorCode.WRONG_TOKEN.getCode(), SummarErrorCode.WRONG_TOKEN.getMessage(), e);
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        chain.doFilter(request, response);
    }
}
