package test.spring.mvc.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.mvc.bean.MemberDTO;

public interface MemberMapper {
	public int loginCheck(MemberDTO dto);
	public MemberDTO member(String id);
	public void memberUpdate(MemberDTO dto);
	public int statusChange(@Param("id") String id,@Param("passwd") String passwd);
	public int getStatus(String id);
	public List<MemberDTO> memberList();
	public int adminStatsChange(MemberDTO dto);
	public int profileChange(MemberDTO dto);
}

