package sems.service;

import java.util.List;
import java.util.Map;

import sems.model.Message;

public interface SendService {
	String selectActiveYn() throws Exception;
	int selectTargetCustomer() throws Exception;
	int insertSms(List<Map<String, Object>> targetList) throws Exception;
}
