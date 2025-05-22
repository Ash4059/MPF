package com.example.MPF.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CustomInterceptor implements HandlerInterceptor {


    /**
     * This method is called before the handler is executed. It is useful for
     * performing some kind of validation or authentication checks before the
     * handler is executed.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Inside pre handle method");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * This method is called after the handler has been executed. It is useful for
     * performing cleanup operations such as closing resources.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Inside after handle method");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    
    /**
     * This method is called after the handler has been executed, but before the
     * DispatcherServlet renders the view. This method can expose the model
     * objects to the view via the given ModelAndView. This method will run 
     * only if no exception occurred.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Inside post handle method");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
