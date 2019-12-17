package sems;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import sems.config.ChainedTxConfig;
import sems.config.Db1DataConfig;
import sems.config.Db2DataConfig;
import sems.config.exception.SemsException;
import sems.model.Cust;
import sems.model.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Db1DataConfig.class, Db2DataConfig.class, ChainedTxConfig.class })
public class MybatisTest {

  private static Logger logger = LoggerFactory.getLogger(MybatisTest.class);

  @Autowired
  @Qualifier("sqlSessionTemplate")
  private SqlSessionTemplate sqlSession;

  @Autowired
  @Qualifier("sqlSessionTemplate2")
  private SqlSessionTemplate sqlSession2;

  @Test
  public void getSendedMsgList() {
    List<Message> sended = sqlSession2.selectList("sems.message.selectSendedMessages");
    sended.stream().forEach(message -> logger.debug(message.toString()));
  }

  @Transactional(rollbackFor=Exception.class)
  @Test
  public void insertSMS() {
    Message message = new Message("SMS");
    message.setSendDate(new Date());
    message.setPhone("01079189218");
    message.setCallback("01031395221");
    message.setMsg("메세지 전송 테스트2");

    sqlSession2.insert("sems.message.insertSMS", message);
    
    Cust cust = new Cust();
    cust.setCustCd(5);
    cust.setCusName("테스트업체");
    cust.setCustNm("테스터");
    cust.setCusTel("0000");
    cust.setCusHp("1111");
    cust.setCycle(10);
    cust.setUseYn(true);
    
    sqlSession.update("sems.cust.updateCust", cust);
    
    throw new SemsException("오류야");
  }
}
