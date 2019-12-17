package sems.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class User {
  @NotNull
  @Size(max = 20)
  private String id;
  
  @Size(max = 20)
  private String pwd;

  @Size(max = 20)
  private String name;
  
  @Size(max = 5)
  private String jw;

  @Size(max = 30)
  private String auth;

  public User() {
  };

  public User(String id, String pwd) {
    this.id = id;
    this.pwd = pwd;
  }

  public User(String id, String pwd, String name, String jw, String auth) {
    this(id, pwd);
    this.name = name;
    this.jw = jw;
    this.auth = auth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }

  public String getJw() {
    return jw;
  }

  public void setJw(String jw) {
    this.jw = jw;
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
  }
}
