package sems.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import sems.model.Message;

@Repository("sendDAO")
public class SendDAO {	
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	  
	@Autowired
	@Qualifier("sqlSessionTemplate2")
	private SqlSessionTemplate sqlSession2;
	
	public String selectActiveYn() throws Exception {
		
		return sqlSession.selectOne("sems.message.selectActiveYn", null);
	}

	public List<Map<String, Object>> selectTargetCustomer() throws Exception {
		
		return (List<Map<String, Object>>) sqlSession.selectMap("sems.message.selectTargetCustomer", null);
	}

	public int insertSms(Message message) throws Exception {
		return sqlSession2.insert("sems.message.insertSMS", message);
	}
}