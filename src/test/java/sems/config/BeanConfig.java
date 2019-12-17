package sems.config;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages= {"sems.controller", "sems.service", "sems.dao"})
public class BeanConfig {
}
