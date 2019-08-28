package project.kirei.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AppHandlerInterceptor extends HandlerInterceptorAdapter  {

	private static final Logger logger = LoggerFactory.getLogger(AppHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("--------------------------------Interceptor preHandle");

        HttpSession session = request.getSession();

        if (session.getAttribute("userInfo") != null) {

        	return true;

        } else {

        	logger.info("--------------------------------Interceptor!");
        	response.sendRedirect("/kirei");
        	return false;

        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("--------------------------------Interceptor postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
        logger.info("--------------------------------Interceptor afterCompletion" );
    }


}
