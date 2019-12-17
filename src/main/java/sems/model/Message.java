package sems.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Message {
  private int id;
  
  @NotNull
  private String msgType;

  private String subject;

  @NotNull
  private String msg;

  @NotNull
  @Max(11)
  private String phone;

  @NotNull
  @Max(11)
  private String callback;

  private String type;
  private String status;
  private Date sendDate;
  private Date rsltDate;
  private String etc1;
  private String etc2;
  private String etc3;
  
  public Message() {}
  
  public Message(String msgType) {
    this.msgType = msgType;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public String getMsgType() {
    return msgType;
  }
  
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCallback() {
    return callback;
  }

  public void setCallback(String callback) {
    this.callback = callback;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getSendDate() {
    return sendDate;
  }

  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
  }

  public Date getRsltDate() {
    return rsltDate;
  }

  public void setRsltDate(Date rsltDate) {
    this.rsltDate = rsltDate;
  }

  public String getEtc1() {
    return etc1;
  }

  public void setEtc1(String etc1) {
    this.etc1 = etc1;
  }

  public String getEtc2() {
    return etc2;
  }

  public void setEtc2(String etc2) {
    this.etc2 = etc2;
  }

  public String getEtc3() {
    return etc3;
  }

  public void setEtc3(String etc3) {
    this.etc3 = etc3;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, "msgType", "id");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "msgType", "id");
  }
  
  @Override
  public String toString() {
    return "Message{id=" + id +
        ",msgType=" + msgType +
        ",subject='" + subject + "\'" +
        ",msg='" + msg + "\'" +
        ",phone='" + phone + "\'" +
        ",callback='" + callback + "\'" +
        ",type=" + type +
        ",status=" + status +
        ",sendDate=" + dateFormat().format(sendDate) +
        ",rsltDate=" + dateFormat().format(rsltDate) +
        ",etc1='" + etc1 + "\'" +
        ",etc2='" + etc2 + "\'" +
        ",etc3='" + etc3 +
        "}";
  }
  
  private SimpleDateFormat dateFormat() {
    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  }
}