package test.spring.mvc.service;

import java.util.List;

import test.spring.mvc.bean.MemberDTO;

public interface MemberService {
	public int loginCheck(MemberDTO dto);
	public void userInput(MemberDTO dto);
	public void userUpdate(MemberDTO dto);
	public MemberDTO getUser(String id);
	public int userDelete(String id,String passwd);
	public int getStatus(String id);
	public List<MemberDTO> memberList();
	public int adminStatsChange(MemberDTO dto);
	
	
}
