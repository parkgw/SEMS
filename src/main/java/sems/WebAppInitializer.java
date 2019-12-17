package sems;

import java.security.Security;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import sems.config.ChainedTxConfig;
import sems.config.Db1DataConfig;
import sems.config.Db2DataConfig;
import sems.config.RootConfig;
import sems.config.WebConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] { RootConfig.class, Db1DataConfig.class, Db2DataConfig.class, ChainedTxConfig.class,
        Security.class };
  } 

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { WebConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  @Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
    encodingFilter.setEncoding("UTF-8");
    encodingFilter.setForceEncoding(true);

    return new Filter[] { encodingFilter };
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);

    servletContext.addListener(new HttpSessionListener() {
      @Override
      public void sessionCreated(HttpSessionEvent e) {
        e.getSession().setMaxInactiveInterval(10 * 60);
      }

      @Override
      public void sessionDestroyed(HttpSessionEvent e) {
      }
    });
  }

   @Override
  protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
     DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
     dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
     
     return dispatcherServlet;
  }
}
