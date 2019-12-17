package sems;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class SpringSecurityTest {
  private static final Logger logger = LoggerFactory.getLogger(SpringSecurityTest.class);
  
  
  @Test
  public void standardPasswordEncoder() {
    StandardPasswordEncoder encoder = new StandardPasswordEncoder("gwpark+csyu");
    
    logger.info(encoder.encode("2222"));
  }
}
