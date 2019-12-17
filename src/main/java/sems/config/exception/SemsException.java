package sems.config.exception;

public class SemsException extends RuntimeException {
  private static final long serialVersionUID = -1021781349384705787L;

  private ErrorObject errorObject;

  public SemsException() {
    super();
  }

  public SemsException(String message) {
    this("", message);
  }
  
  public SemsException(String code, String message) {
    super(message);
    errorObject = new ErrorObject(code, message);
  }

  public ErrorObject getErrorObject() {
    return errorObject;
  }
}

class ErrorObject {
  private String errCode;
  private String errMessage;

  public ErrorObject() {
  }

  public ErrorObject(String errorMessage) {
    this("", "errorMessage");
  }

  public ErrorObject(String errCode, String errorMessage) {
    this.errCode = errCode;
    this.errMessage = errorMessage;
  }

  public String getErrCode() {
    return errCode;
  }

  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  public String getErrMessage() {
    return errMessage;
  }

  public void setErrMessage(String errMessage) {
    this.errMessage = errMessage;
  }
}
