package sems.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import sems.model.Cust;

@Repository
public class CustDao {
  
  @Autowired
  @Qualifier("sqlSessionTemplate")
  private SqlSessionTemplate sqlSession;
  
  public List<Cust> selectCustList() {
    return sqlSession.selectList("sems.cust.selectCustList");
  }
  
  public int createCust(Cust cust) {
    return sqlSession.insert("sems.cust.insertCust", cust);
  }
  
  public int modifyCust(Cust cust) {
    return sqlSession.update("sems.cust.updateCust", cust);
  }
}
