package sems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import sems.config.security.LoginFailureHandler;
import sems.config.security.LoginSuccessHandler;
import sems.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("utf-8");
    filter.setForceEncoding(true);
    http.addFilterBefore(filter, CsrfFilter.class);
    http.csrf().disable();
    http
      .authorizeRequests()
      .antMatchers("/resources/**", "/login", "/404").permitAll()
        .antMatchers("/agency").access("hasRole('ROLE_B') or hasRole('ROLE_C')")
        .antMatchers("/**").access("hasRole('ROLE_A')")
        .and()
      .formLogin()
        .loginPage("/login")
        .usernameParameter("userid")
        .passwordParameter("passwd")
        .successHandler(new LoginSuccessHandler("/"))
        .failureHandler(new LoginFailureHandler())
        .and()
      .logout()
        .logoutRequestMatcher(new RegexRequestMatcher("/logout", null))
        .logoutSuccessUrl("/");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(new StandardPasswordEncoder("gwpark+csyu"));
  }
}
