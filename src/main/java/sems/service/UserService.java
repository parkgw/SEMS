package sems.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import sems.model.ProfileForm;

public interface UserService extends UserDetailsService {
  public void modifyUserInfo(ProfileForm form);
}
