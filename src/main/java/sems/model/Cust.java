package sems.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Cust {
  private long custCd;
  private String custNm;
  private String cusName;
  private String cusTel;
  private String cusHp;
  private long cycle;
  private boolean useYn;
  private Date inputDay;
  private String inputId;
  private Date updateDay;
  private String updateId;

  public long getCustCd() {
    return custCd;
  }

  public void setCustCd(long custCd) {
    this.custCd = custCd;
  }

  public String getCustNm() {
    return custNm;
  }

  public void setCustNm(String custNm) {
    this.custNm = custNm;
  }

  public String getCusName() {
    return cusName;
  }

  public void setCusName(String cusName) {
    this.cusName = cusName;
  }

  public String getCusTel() {
    return cusTel;
  }

  public void setCusTel(String cusTel) {
    this.cusTel = cusTel;
  }

  public String getCusHp() {
    return cusHp;
  }

  public void setCusHp(String custHp) {
    this.cusHp = custHp;
  }

  public long getCycle() {
    return cycle;
  }

  public void setCycle(long cycle) {
    this.cycle = cycle;
  }

  public boolean isUseYn() {
    return useYn;
  }

  public void setUseYn(boolean useYn) {
    this.useYn = useYn;
  }

  public Date getInputDay() {
    return inputDay;
  }

  public void setInputDay(Date inputDay) {
    this.inputDay = inputDay;
  }

  public String getInputId() {
    return inputId;
  }

  public void setInputId(String inputId) {
    this.inputId = inputId;
  }

  public Date getUpdateDay() {
    return updateDay;
  }

  public void setUpdateDay(Date updateDay) {
    this.updateDay = updateDay;
  }

  public String getUpdateId() {
    return updateId;
  }

  public void setUpdateId(String updateId) {
    this.updateId = updateId;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
  }
}
