package sems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sems.model.User;

@Controller
@RequestMapping
public class SemsController {
  private static final Logger logger = LoggerFactory.getLogger(SemsController.class);

  @RequestMapping(method = RequestMethod.GET, value = "/login")
  public String login(Model model) {
    model.addAttribute(new User());
    return "login";
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "/home")
  public String home() {
    return "home";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/agency")
  public String agency() {
    return "agency";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/chart")
  public String chart() {
    return "chart";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/grid")
  public String grid() {
    return "grid";
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "/check")
  public String check() {
    return "check";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/404")
  public String notFoudPage() {
    return "404";
  }
}
