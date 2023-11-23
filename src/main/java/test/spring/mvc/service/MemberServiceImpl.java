package test.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.mvc.bean.MemberDTO;
import test.spring.mvc.repository.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper mapper;
	
	@Override
	public int loginCheck(MemberDTO dto) {
		
		return mapper.loginCheck(dto);
	}

	@Override
	public void userInput(MemberDTO dto) {
		
	}

	@Override
	public MemberDTO getUser(String id) {
		return mapper.member(id);
	}

	@Override
	public void userUpdate(MemberDTO dto) {
		mapper.memberUpdate(dto);
	}

	@Override
	public int userDelete(String id, String passwd) {
			
		return mapper.statusChange(id, passwd);
	}

	@Override
	public int getStatus(String id) {
		
		return mapper.getStatus(id);
	}

	@Override
	public List<MemberDTO> memberList() {
		List<MemberDTO> list = mapper.memberList();
		return list;
	}

	@Override
	public int adminStatsChange(MemberDTO dto) {
		
		return mapper.adminStatsChange(dto);
	}

	
	
	
	
}
