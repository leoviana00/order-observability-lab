package io.viana.order_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class HttpLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(HttpLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        long start = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, response);
        } finally {

            long duration = System.currentTimeMillis() - start;

            try {

                MDC.put("http.method", request.getMethod());
                MDC.put("http.path", request.getRequestURI());
                MDC.put("http.status_code", String.valueOf(response.getStatus()));
                MDC.put("duration.ms", String.valueOf(duration));

                MDC.put("event.type", "technical");
                MDC.put("event.name", "http_request_completed");

                log.info("HTTP request completed");

            } finally {

                MDC.remove("http.method");
                MDC.remove("http.path");
                MDC.remove("http.status_code");
                MDC.remove("duration.ms");
                MDC.remove("event.type");
                MDC.remove("event.name");
            }
        }
    }
}