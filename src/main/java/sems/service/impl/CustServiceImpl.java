package sems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sems.config.exception.SemsException;
import sems.config.security.SemsUser;
import sems.dao.CustDao;
import sems.model.Cust;
import sems.service.CustService;

@Service("custService")
public class CustServiceImpl implements CustService {
  @Autowired
  CustDao custDao;

  @Override
  public List<Cust> selectCustList() {
    return custDao.selectCustList();
  }

  @Override
  @Transactional(transactionManager="custTxManager", rollbackFor= {Exception.class})
  public void createCust(Cust cust) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    SemsUser user = (SemsUser) auth.getPrincipal();
    cust.setInputId(user.getUsername());

    if (custDao.createCust(cust) < 1) {
      throw new SemsException("거래처를 등록하는데 실패하였습니다.");
    }
  }

  @Override
  @Transactional(transactionManager="custTxManager", rollbackFor= {Exception.class})
  public void modifyCust(Cust cust) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    SemsUser user = (SemsUser) auth.getPrincipal();
    cust.setUpdateId(user.getUsername());

    if (custDao.modifyCust(cust) < 1) {
      throw new SemsException("거래처정보를 수정하는데 실패하였습니 다.");
    }
  }
}
