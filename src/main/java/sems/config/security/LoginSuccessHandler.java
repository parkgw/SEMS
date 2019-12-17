package sems.config.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.google.gson.Gson;

import sems.model.User;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

  public LoginSuccessHandler(String defaultTargetUrl) {
    super.setDefaultTargetUrl(defaultTargetUrl);
  }
  
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws ServletException, IOException {
    String accept = request.getHeader(HttpHeaders.ACCEPT);

    if (StringUtils.indexOf(accept, "html") > -1) {
      super.onAuthenticationSuccess(request, response, authentication);
    } else if (StringUtils.indexOf(accept, "json") > -1) {
      response.setContentType("application/json");
      response.setCharacterEncoding("utf-8");
      
      HttpSession session = request.getSession();
      User custInfo = (User)session.getAttribute("custInfo");
      
      String data = StringUtils.join(new String[] { 
          "{ \"custInfo\" : ", new Gson().toJson(custInfo), "}"
      });

      PrintWriter out = response.getWriter();
      out.print(data);
      out.flush();
      out.close();
    } else {
      logger.error("Unsupported format error");
      throw new ServletException("Unsupported format error");
    }
  }
}
