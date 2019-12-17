package sems.controller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class SemsControllerTest {

  private static final Logger logger = LoggerFactory.getLogger(SemsControllerTest.class);
  
  @Test
  public void loginPage() throws Exception {
    SemsController controller = new SemsController();
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setViewResolvers(new InternalResourceViewResolver())
        .build();

    mockMvc.perform(MockMvcRequestBuilders.get("/login"))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
