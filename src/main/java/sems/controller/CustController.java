package sems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import sems.config.exception.SemsException;
import sems.model.Cust;
import sems.service.CustService;

@Controller
@RequestMapping("/agencies")
public class CustController {
  private static final Logger logger = LoggerFactory.getLogger(CustController.class);

  @Autowired
  CustService custService;
  
  @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
  public @ResponseBody ResponseEntity<?> selectCustList() {
    List<Cust> custList = custService.selectCustList();

    return new ResponseEntity<String>(new Gson().toJson(custList), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
  public @ResponseBody ResponseEntity<?> createCust(@RequestBody Cust cust) throws SemsException {
    custService.createCust(cust);

    Map<String, Object> result = new HashMap<String, Object>();
    result.put("success", true);
    result.put("message", "거래처를 성공적으로 등록하였습니다.");
    result.put("custCd", cust.getCustCd());

    return new ResponseEntity<String>(new Gson().toJson(result), HttpStatus.OK);
  }

  @RequestMapping(value = "/{custCd}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
  public @ResponseBody ResponseEntity<?> modifyCust(@PathVariable("custCd") String custCd, @RequestBody Cust cust) {
    custService.modifyCust(cust);

    Map<String, Object> result = new HashMap<String, Object>();
    result.put("success", true);
    result.put("message", "거래처를 성공적으로 등록하였습니다.");

    return new ResponseEntity<String>(new Gson().toJson(result), HttpStatus.OK);
  }
}
