package com.acme.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Gates internal/admin-only endpoints behind a shared key sent in the
 * X-API-Key header. This never applies to POST /api/orders or GET
 * /api/products, since the public storefront calls those directly and
 * any key baked into the frontend bundle would be visible to anyone.
 */
@Component
public class ApiKeyFilter implements Filter {

    private static final String HEADER_NAME = "X-API-Key";

    @Value("${admin.api.key}")
    private String expectedApiKey;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isProtected(request.getRequestURI(), request.getMethod())) {
            String providedKey = request.getHeader(HEADER_NAME);
            if (providedKey == null || !providedKey.equals(expectedApiKey)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Missing or invalid API key\"}");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isProtected(String path, String method) {
        boolean isOrderRead = path.startsWith("/api/orders") && "GET".equals(method);
        boolean isProductWrite = path.equals("/api/products") && "POST".equals(method);
        return isOrderRead || isProductWrite;
    }
}