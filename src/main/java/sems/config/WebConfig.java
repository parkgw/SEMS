package sems.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.scheduling.annotation.EnableScheduling;

import sems.config.formatters.DateFormatter;
import sems.config.interceptor.SemsInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan({ "sems.controller" })
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware  {

  private ApplicationContext applicationContext;
  
  public WebConfig() {
    super();
  }
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
  
  @Bean
  public ApplicationContext applicationContext() {
    return this.applicationContext;
  }
  
  @Bean
  public HandlerExceptionResolver exceptionResolver() {
    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
    Properties properties = new Properties();
    properties.setProperty(NoHandlerFoundException.class.getName(), "404");
    properties.setProperty(DefaultHandlerExceptionResolver.class.getName(), "404");
    exceptionResolver.setExceptionMappings(properties);
    exceptionResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
    
    return exceptionResolver;
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    resolver.setExposeContextBeansAsAttributes(true);
    resolver.setOrder(Ordered.LOWEST_PRECEDENCE);

    return resolver;
  }

  @Bean
  public ViewResolver tilesViewResolver() {
    TilesViewResolver resolver = new TilesViewResolver();
    resolver.setOrder(1);

    return resolver;
  }

  @Bean
  public TilesConfigurer tilesConfigurer() {
    TilesConfigurer tiles = new TilesConfigurer();
    String[] definitions = { "/WEB-INF/layout/tiles.xml", "/WEB-INF/**/tiles.xml" };

    tiles.setDefinitions(definitions);
    tiles.setCheckRefresh(true);

    return tiles;
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    messageSource.setBasename("/META-INF/i18n/messages");
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setCacheSeconds(10);

    return messageSource;
  }
  
  @Override
  public void addFormatters(FormatterRegistry registry) {
    super.addFormatters(registry);
    registry.addFormatter(dateFormatter());
  }

  @Bean
  public DateFormatter dateFormatter() {
    return new DateFormatter();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    super.addResourceHandlers(registry);
    registry.addResourceHandler("/images/**").addResourceLocations("/images/");
    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
  }

  @Bean
  SemsInterceptor semsInterceptor() {
    return new SemsInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
    registry.addInterceptor(semsInterceptor());
  }
}
