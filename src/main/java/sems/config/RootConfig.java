package sems.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages={"sems"},
  excludeFilters= {
      @Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
  })
public class RootConfig {
}
