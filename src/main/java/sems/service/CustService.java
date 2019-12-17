package sems.service;

import java.util.List;

import sems.model.Cust;

public interface CustService {
  public List<Cust> selectCustList();
  public void createCust(Cust cust);
  public void modifyCust(Cust cust);
}
