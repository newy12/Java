package com.summar.summar.filter;

import org.json.JSONObject;
import com.summar.summar.common.SummarJwtException;
import com.summar.summar.dto.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        } catch (SummarJwtException ex){
            setJwtErrorResponse(HttpStatus.UNAUTHORIZED, response, ex);
        }
    }

    public void setJwtErrorResponse(HttpStatus status, HttpServletResponse res, SummarJwtException ex) throws IOException {
        res.setStatus(status.value());
        res.setContentType("application/json; charset=UTF-8");

        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setErrorCode(ex.getCode());
        apiResponseMessage.setErrorMessage(ex.getMessage());
        JSONObject responseJson = new JSONObject(apiResponseMessage);

        res.getWriter().print(responseJson);
    }
}
