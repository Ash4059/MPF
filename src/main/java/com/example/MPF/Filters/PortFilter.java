package com.example.MPF.Filters;

import com.example.MPF.Ingresess.RestIngress;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class PortFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(PortFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        int requestPort = request.getLocalPort();
        int ALLOWED_PORT = Integer.parseInt(RestIngress.getInstance().getConfig().get("port").toString());
        if(requestPort != ALLOWED_PORT){
            logger.warn("Request from unauthorized port {} for URI {} rejected.", requestPort, request.getRequestURI());
            // Reject the request with a 403 Forbidden status
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access denied from this port.");
            return; // Stop processing the filter chain
        }

        // If the port is correct, continue to the next filter in the chain
        logger.info("Request from allowed port {} for URI {} accepted. Proceeding with filter chain.", requestPort, request.getRequestURI());
        filterChain.doFilter(request, response);
    }

}
