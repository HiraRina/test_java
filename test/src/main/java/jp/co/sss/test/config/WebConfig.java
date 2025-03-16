package jp.co.sss.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.sss.test.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registory) {
		registory.addResourceHandler("/test/img/**")
				 .addResourceLocations("classpath:/static/img/");
		
	}
	//ログインチェック機能を追加
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
			//全てにパスで適用
			.addPathPatterns("/**")
			.excludePathPatterns(
					"/login","/login/complete","/create",
					"/create/input","/css/**","/js/**","/img/**");
	}
}
