package com.startyup.sarathi.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    
        String connectionHeader = request.getHeader("connection");
        if ("Keep-Alive".equalsIgnoreCase(connectionHeader) && request.getContentLength() == 0) {
            return true; // Skip logging for Keep-Alive requests
        }

        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Remote Address: " + request.getRemoteAddr());
        System.out.println("Query String: " + request.getQueryString());
        System.out.println("Headers: ");

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> 
                    System.out.println(headerName + ": " + request.getHeader(headerName))
                );

        return true; 
    }
}
