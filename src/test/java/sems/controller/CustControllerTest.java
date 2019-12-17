package sems.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import sems.WebAppInitializer;
import sems.config.BeanConfig;
import sems.config.ChainedTxConfig;
import sems.config.Db1DataConfig;
import sems.config.Db2DataConfig;

@WebAppConfiguration
@ContextConfiguration(classes= {BeanConfig.class, WebAppInitializer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustControllerTest {
  
  MockMvc mockMvc;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(new CustController()).build();
  }
  
  @Test
  public void selectCustList() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/agencies"))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}