package sems.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import sems.dao.SendDAO;
import sems.model.Message;

@Service("SendService")
public class SendServiceImpl implements SendService{
	
	@Resource(name="sendDAO")
	private SendDAO sendDAO;

	@Override
	public String selectActiveYn() throws Exception {

		return null;
	}

	@Override
	public int selectTargetCustomer() throws Exception {
		
		List<Map<String, Object>> targetList = sendDAO.selectTargetCustomer();
		
		int sendCnt = 0;
		int sendCntSum = 0;
		
		//다수의 거래처일 경우
		for(int i=1; i<targetList.size(); i++) {
			//대상이 되는 거래처가 있는 경우
			if(targetList.get(i) != null && !targetList.get(i).equals("")) {
    			Map<String, Object> targetMap = targetList.get(i);
    			
    			Message message = new Message("SMS");
    		    message.setSendDate(new Date());
    		    message.setPhone(targetMap.get("CUS_HPNO").toString());
    		    message.setCallback("01031395221");
    		    //날짜 변환
    		    String nextCheckDay = targetMap.get("NEXT_CHECK_DAY").toString();
    		    nextCheckDay = nextCheckDay.substring(1, 4) + "년 " + nextCheckDay.substring(5, 2) + "월 " + nextCheckDay.substring(7, 2) + "일";
    		    
    		    String msg = "안녕하십니까? (주)GW그룹입니다. " + targetMap.get("CUST_NM").toString() + "님의 검사일자가 " + nextCheckDay + "로 예정되어 있습니다. 일정 착오 없으시기 바랍니다. 일정조율 및 기타 문의사항은 010-3139-5221로 연락 부탁드립니다.";
    		    message.setMsg(msg);
    			
    			sendCnt = sendDAO.insertSms(message);
    			sendCntSum += sendCnt;
    		}
		}

		return sendCntSum;
	}

	@Override
	public int insertSms(List<Map<String, Object>> targetList) throws Exception {
		
		int sendCntSum = 0;
		int sendCnt = 0;
		
		for(int i=0; i<targetList.size(); i++)
		{
			targetList.get(i).get("");
			
			Message message = new Message("SMS");
		    message.setSendDate(new Date());
		    message.setPhone("01079189218");
		    message.setCallback("01031395221");
		    message.setMsg("메세지 전송 테스트");
		    
		    sendCnt = sendDAO.insertSms(message);
		    
		    sendCntSum += sendCnt;
		}
		
		return sendCntSum; 
	    
	}
	
}
