package com.startyup.sarathi.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Remote Address: " + request.getRemoteAddr());
        System.out.println("Query String: " + request.getQueryString());
        System.out.println("Headers: ");

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> 
                    System.out.println(headerName + ": " + request.getHeader(headerName))
                );

        return true; // Continue with the request processing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (response instanceof CustomResponseWrapper) {
            String responseContent = ((CustomResponseWrapper) response).getContent();
            System.out.println("Response Content: " + responseContent);
        }
    }
}
