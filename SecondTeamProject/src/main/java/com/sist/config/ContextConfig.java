package com.sist.config;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@ComponentScan(basePackages = "com.sist.*")
@EnableAspectJAutoProxy
@EnableWebMvc
public class ContextConfig implements WebMvcConfigurer {

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
	    return new StringHttpMessageConverter(StandardCharsets.UTF_8);
	}
	
	
	//뷰리솔버
	@Bean("viewResolver")
	//@Scope("prototype") scope="prototype"
	public ViewResolver viewResolver() {
		InternalResourceViewResolver ir= new InternalResourceViewResolver();
		ir.setPrefix("/");
		ir.setSuffix(".jsp");
		ir.setOrder(1);
		return ir;
	}
	
	//타일즈
	
	  @Bean("tilesConfigurer")
	    public TilesConfigurer tilesConfigurer() {
	        TilesConfigurer tilesConfigurer = new TilesConfigurer();
	        tilesConfigurer.setDefinitions("/WEB-INF/config/tiles.xml");
	        return tilesConfigurer;
	    }

	  @Bean
	  public ViewResolver tilesViewResolver() {
	      UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
	      viewResolver.setViewClass(org.springframework.web.servlet.view.tiles3.TilesView.class);
	      viewResolver.setOrder(0); // Set the order if needed
	      return viewResolver;
	  }
	
	//파일업로드
	
	@Bean("multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver=
					new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSizePerFile(100*1024*1024);//100MB
		
		return multipartResolver;
	}
	
	
	
/*
 * 인터셉터 예시 	인터셉터 등록후 addInterceptors에 registry 할것
 */
//	@Bean
//    public FoodInterceptor foodInterceptor() {
//        return new FoodInterceptor();
//    }
	
	
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
//	        registry.addInterceptor(foodInterceptor())
//	                .addPathPatterns("/**"); <--예시
	    }
	
	
}
