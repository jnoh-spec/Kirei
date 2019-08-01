package project.kirei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import project.kirei.interceptor.AppHandlerInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private AppHandlerInterceptor appHandlerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		System.out.println("InterceptorConfig!");

		registry.addInterceptor(appHandlerInterceptor).addPathPatterns("/kirei/*")
													  .excludePathPatterns("/kirei")
													  .excludePathPatterns("/kirei/userInsert")
													  .excludePathPatterns("/kirei/login");

	}

}
