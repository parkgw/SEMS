package sems.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sems.config.exception.SemsException;
import sems.config.security.SemsUser;
import sems.dao.UserDao;
import sems.model.ProfileForm;
import sems.model.User;
import sems.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  @Qualifier("sqlSessionTemplate")
  private SqlSession sqlSession;

  @Autowired
  @Qualifier("sqlSessionTemplate2")
  private SqlSession sqlSession2;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.findByUsername(username);

    if (user != null) {
      Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

      String auth = user.getAuth();
      String[] authArr = auth.split(",");

      for (String role : authArr) {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
      }

      // Default ROLE (추후 상세 권한에 대한 관리시 삭제해야 함)
      if (authorities.isEmpty()) {
        authorities.add(new SimpleGrantedAuthority("ROLE_B"));
      }

      return new SemsUser(user.getId(), user.getPwd(), user.getName(), user.getJw(), authorities);
    }

    throw new UsernameNotFoundException("User '" + username + "' not found.");
  }

  @Override
  @Transactional(transactionManager="custTxManager", rollbackFor= {Exception.class})
  public void modifyUserInfo(ProfileForm form) {
    StandardPasswordEncoder encoder = new StandardPasswordEncoder("gwpark+csyu");

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    SemsUser user = (SemsUser) auth.getPrincipal();

    if (!user.getUsername().equals(form.getUserid())) {
      throw new SemsException("정상적인 요청이 아닙니다.");
    }

    User currUser = userDao.findByUsername(form.getUserid());
    if (!encoder.matches(form.getOldPasswd(), currUser.getPwd())) {
      throw new SemsException("oldPasswd", "기존 비밀번호가 다릅니다.");
    }

    if (!form.equalsPasswords()) {
      throw new SemsException("newPasswd", "새로운 비밀번호가 일치하지 않습니다.");
    }

    if (!"".equals(form.getNewPasswd())) {
      form.setNewPasswd(new StandardPasswordEncoder("gwpark+csyu").encode(form.getNewPasswd()));
    }

    if (userDao.modifyUserInfo(form) < 1) {
      throw new SemsException("사용자 정보를 변경하는데 실패하였습니다.");
    }

    if (!form.getJw().equals(user.getJw())) {
      user.setJw(form.getJw());
    }

    Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) user.getAuthorities();
    Set<GrantedAuthority> newAuthorities = new HashSet<GrantedAuthority>();
    String auths = form.getAuth();
    StringTokenizer st = new StringTokenizer(auths, ",");

    while (st.hasMoreTokens()) {
      newAuthorities.add(new SimpleGrantedAuthority("ROLE_" + st.nextToken()));
    }

    if (!user.compareAuthorities(authorities, newAuthorities)) {
      user.setAuthorities(newAuthorities);

      // 권한 변경 후 즉시 적용
      Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(),
          newAuthorities);
      SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
  }
}
