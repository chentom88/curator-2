package io.pivotal.hackday.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by pivotal on 5/6/16.
 */

@Configuration
@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**").addResourceLocations("css/").setCachePeriod(31556926);
        registry.addResourceHandler("img/**").addResourceLocations("img/").setCachePeriod(31556926);
        registry.addResourceHandler("js/**").addResourceLocations("js/").setCachePeriod(31556926);
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}