package sems.config.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class SemsUser implements UserDetails, CredentialsContainer {
  private static final long serialVersionUID = -3182114122470874735L;

  private final String username;
  private String password;
  private Set<GrantedAuthority> authorities;
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;

  /* extention variables */
  private String name;
  private String jw;

  public SemsUser(String username, String password, String name, String jw,
      Collection<? extends GrantedAuthority> authorities) {
    this(username, password, name, jw, true, true, true, true, authorities);
  }

  public SemsUser(String username, String password, String name, String jw, boolean enabled, boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

    if (((username == null) || "".equals(username)) || (password == null)) {
      throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
    }

    this.username = username;
    this.password = password;
    this.name = name;
    this.jw = jw;
    this.enabled = enabled;
    this.accountNonExpired = accountNonExpired;
    this.credentialsNonExpired = credentialsNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
  }

  public Collection<GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void eraseCredentials() {
    password = null;
  }

  private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
    Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
    // Ensure array iteration order is predictable (as per
    // UserDetails.getAuthorities() contract and SEC-717)
    SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());

    for (GrantedAuthority grantedAuthority : authorities) {
      Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
      sortedAuthorities.add(grantedAuthority);
    }

    return sortedAuthorities;
  }

  private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    public int compare(GrantedAuthority g1, GrantedAuthority g2) {
      // Neither should ever be null as each entry is checked before adding it to
      // the set.
      // If the authority is null, it is a custom authority and should precede
      // others.
      if (g2.getAuthority() == null) {
        return -1;
      }

      if (g1.getAuthority() == null) {
        return 1;
      }

      return g1.getAuthority().compareTo(g2.getAuthority());
    }
  }

  /**
   * Returns {@code true} if the supplied object is a {@code User} instance with
   * the same {@code username} value.
   * <p>
   * In other words, the objects are equal if they have the same username,
   * representing the same principal.
   */
  @Override
  public boolean equals(Object rhs) {
    if (rhs instanceof User) {
      return username.equals(((SemsUser) rhs).username);
    }
    return false;
  }

  /**
   * Returns the hashcode of the {@code username}.
   */
  @Override
  public int hashCode() {
    return username.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString()).append(": ");
    sb.append("Username: ").append(this.username).append("; ");
    sb.append("Password: [PROTECTED]; ");
    sb.append("Name: ").append(this.name).append("; ");
    sb.append("Jw: ").append(this.jw).append("; ");
    sb.append("Enabled: ").append(this.enabled).append("; ");
    sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
    sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
    sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

    if (!authorities.isEmpty()) {
      sb.append("Granted Authorities: ");

      boolean first = true;
      for (GrantedAuthority auth : authorities) {
        if (!first) {
          sb.append(",");
        }
        first = false;

        sb.append(auth);
      }
    } else {
      sb.append("Not granted any authorities");
    }

    return sb.toString();
  }
  
  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities)); 
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJw() {
    return jw;
  }

  public void setJw(String jw) {
    this.jw = jw;
  }
  
  public boolean compareAuthorities(Collection<? extends GrantedAuthority> auth1, Collection<? extends GrantedAuthority> auth2) {
    return auth1.containsAll(auth2) && auth2.containsAll(auth1);
  }
}
