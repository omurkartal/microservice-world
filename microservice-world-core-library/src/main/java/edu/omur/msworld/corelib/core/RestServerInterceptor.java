package edu.omur.msworld.corelib.core;

import edu.omur.msworld.corelib.model.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class RestServerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(RestServerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //TODO:
        String consumerService = request.getHeader(Constants.KEY_CONSUMER_SERVICE);
        logger.info(Constants.KEY_CONSUMER_SERVICE + ": " + consumerService);

        String uuid = request.getHeader(Constants.KEY_UUID);
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        logger.info(Constants.KEY_UUID + ": " + uuid);
        ThreadContext.put(Constants.KEY_UUID, uuid);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view) {
        //TODO:
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //TODO:
    }
}
