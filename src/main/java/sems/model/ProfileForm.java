package sems.model;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProfileForm {
  @NotNull
  private String userid;
  @NotNull
  private String oldPasswd;
  
  private String newPasswd;
  private String newPasswdRe;
  
  @NotNull
  private String jw;
  private String auth;

  public ProfileForm() {
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getOldPasswd() {
    return oldPasswd;
  }

  public void setOldPasswd(String oldPasswd) {
    this.oldPasswd = oldPasswd;
  }

  public String getNewPasswd() {
    return newPasswd;
  }

  public void setNewPasswd(String newPasswd) {
    this.newPasswd = newPasswd;
  }

  public String getNewPasswdRe() {
    return newPasswdRe;
  }

  public void setNewPasswdRe(String newPasswdRe) {
    this.newPasswdRe = newPasswdRe;
  }

  public String getJw() {
    return jw;
  }

  public void setJw(String jw) {
    this.jw = jw;
  }

  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }
  
  public boolean equalsPasswords() {
    return newPasswd.equals(newPasswdRe);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
