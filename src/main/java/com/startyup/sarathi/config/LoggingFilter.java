package com.startyup.sarathi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Debug logs to track the filter execution
        System.out.println("LoggingFilter: doFilter start");

        // Log request details
        logRequestDetails(request);

        // Wrap the response to capture the output
        CustomResponseWrapper responseWrapper = new CustomResponseWrapper(response);

        // Proceed with the filter chain using the wrapped response
        chain.doFilter(request, responseWrapper);

        // Log the response content
        logResponseDetails(responseWrapper);

        // Ensure the captured content is written back to the actual response
        responseWrapper.copyBodyToResponse();

        // Debug logs to track the filter execution
        System.out.println("LoggingFilter: doFilter end");
    }

    private void logRequestDetails(HttpServletRequest request) {
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Remote Address: " + request.getRemoteAddr());
        System.out.println("Query String: " + request.getQueryString());
        System.out.println("Headers: ");

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> 
                    System.out.println(headerName + ": " + request.getHeader(headerName))
                );
    }

    private void logResponseDetails(CustomResponseWrapper responseWrapper) {
        String responseContent = responseWrapper.getContent();
        System.out.println("Response Content: " + responseContent);
    }
}
