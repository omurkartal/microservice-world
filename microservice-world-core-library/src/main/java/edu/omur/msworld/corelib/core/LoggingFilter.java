package edu.omur.msworld.corelib.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoggingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Value("${microservice-world.core.log-request-response-details:false}")
    private boolean logRequestResponseDetails;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (logRequestResponseDetails) {
            servletRequest = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
            servletResponse = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (logRequestResponseDetails) {
                logRequest((HttpServletRequest) servletRequest);
                logResponse((HttpServletResponse) servletResponse);
            }
        }
    }

    private void logRequest(HttpServletRequest servletRequest) {
        try {
            ContentCachingRequestWrapper requestWrapper = WebUtils.getNativeRequest(servletRequest, ContentCachingRequestWrapper.class);
            logger.info("Request details. [URI: {}] [Parameters: {}] [Headers: {}]\n[Body: {}]"
                    , requestWrapper.getRequestURI()
                    , getRequestParameters(requestWrapper)
                    , getRequestHeaders(requestWrapper)
                    , getRequestBody(requestWrapper));
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    private void logResponse(HttpServletResponse servletResponse) {
        try {
            ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(servletResponse, ContentCachingResponseWrapper.class);
            logger.info("Response details. [Status: {}] [Headers: {}]\n[Body: {}]"
                    , responseWrapper.getStatus()
                    , getResponseHeaders(responseWrapper)
                    , getResponseBody(responseWrapper));
            responseWrapper.copyBodyToResponse();
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    private Object getRequestHeaders(ContentCachingRequestWrapper requestWrapper) {
        return Collections.list(requestWrapper.getHeaderNames())
                .stream()
                .map(name -> name + ":" + Collections.list(requestWrapper.getHeaders(name)))
                .collect(Collectors.joining(","));
    }

    private Object getRequestParameters(ContentCachingRequestWrapper requestWrapper) {
        return requestWrapper.getParameterMap().entrySet().stream()
                .map(parameter -> parameter.getKey() + ":" + Arrays.toString(parameter.getValue()))
                .collect(Collectors.joining(","));
    }

    private Object getRequestBody(ContentCachingRequestWrapper requestWrapper) {
        try {
            return new String(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Error occurred while getting request body!";
    }

    private Object getResponseHeaders(ContentCachingResponseWrapper responseWrapper) {
        return responseWrapper.getHeaderNames()
                .stream()
                .map(name -> name + ":" + responseWrapper.getHeaders(name))
                .collect(Collectors.joining(","));
    }

    private Object getResponseBody(ContentCachingResponseWrapper responseWrapper) {
        try {
            return new String(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Error occurred while getting response body!";
    }
}
