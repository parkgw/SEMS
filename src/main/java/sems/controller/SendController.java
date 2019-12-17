package sems.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sems.service.SendService;

@Controller
@RequestMapping(value = "/send", method = RequestMethod.GET)
public class SendController {
	//Logger부분 구현해야될지
	
	@Autowired
	private SendService sendService;
     
    @RequestMapping(value="/sendMessage.do")
    public void sendMessage() throws Exception{
    	
    	//날짜와 SEQ가 가장 최근 값인 것을 조회
    	String sendActiveYn = sendService.selectActiveYn();
    	
    	if(sendActiveYn.equals("Y")) { // 현재 자동발송 기능이 동작중
    		//대상이 되는 거래처 조회
    		//TB_CUST01, TB_CHECK01, TB_SEND01과 조인해서 TB_CUST01의 사용유무가 Y, TB_CHECK01의 NEXT_CHECK_DAY가 오늘날짜 기준으로 3일 이내
    		//TB_SEND01에서 이미 전송된 내역이 있는지
    		int sendCnt = sendService.selectTargetCustomer();
    		
    	} else { // 현재 자동발송 기능이 중지중
    		//내부담당자 직원에게 메시지가 안나가고 있다고 인폼
    		//또는 파일 로그 생성
    	}         
    }
    
    public ModelAndView sendList() throws Exception {
    	
		return null;
    	
    }
}
