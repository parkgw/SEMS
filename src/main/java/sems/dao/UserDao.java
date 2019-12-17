package sems.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import sems.model.Message;
import sems.model.ProfileForm;
import sems.model.User;

@Repository
public class UserDao {
  
  @Autowired
  @Qualifier("sqlSessionTemplate")
  private SqlSession sqlSession;
  
  @Autowired
  @Qualifier("sqlSessionTemplate2")
  private SqlSession sqlSession2;
  
  public User findByUsername(String username) {
    return sqlSession.selectOne("sems.cust.selectUser", username);
  }
  
  public int modifyUserInfo(ProfileForm form) {
    return sqlSession.update("sems.cust.updateUserInfo", form);
  }
  
  public int insertSms(Message message) {
    return sqlSession2.insert("sems.message.insertSMS", message);
  }
}
