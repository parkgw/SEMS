package sems.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import sems.model.ProfileForm;
import sems.service.UserService;

@RequestMapping("/users")
@Controller
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserService userService;
  
  @RequestMapping(value="/{userid}", method=RequestMethod.PUT, produces="application/json; charset=UTF-8")
  public @ResponseBody ResponseEntity<?> modifyProfile(@RequestBody ProfileForm form) {
    userService.modifyUserInfo(form);
    
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("success", true);
    
    return new ResponseEntity<String>(new Gson().toJson(resultMap), HttpStatus.OK);
  }
}
