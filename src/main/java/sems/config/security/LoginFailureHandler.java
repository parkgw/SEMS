package sems.config.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    String accept = request.getHeader("accept");
    String message = "아이디 혹은 비밀번호가 일치하지 않습니다.";
    
    if (StringUtils.indexOf(accept, "html") > -1) {
      super.onAuthenticationFailure(request, response, exception);
    } else if (StringUtils.indexOf(accept, "json") > -1) {
      response.setContentType("application/json");
      response.setCharacterEncoding("utf-8");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

      String data = StringUtils.join(new String[] { 
          "{ \"message\" : \"", message, "\" }"
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
