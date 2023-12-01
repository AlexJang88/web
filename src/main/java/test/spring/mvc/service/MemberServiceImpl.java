package test.spring.mvc.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public int profileChange(HttpServletRequest request,MultipartFile profile,MemberDTO dto) {
		String filePath = request.getServletContext().getRealPath("/resources/file/user/");
		String contentType = profile.getContentType();
		if(contentType.split("/")[0].equals("image")) {
			String orgName = profile.getOriginalFilename();
			String ext = orgName.substring(orgName.lastIndexOf("."));   
			File copy = new File(filePath+dto.getId()+ext);
			String img = dto.getId()+ext;
			try {
				profile.transferTo(copy);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(img!=null && img.length()>0) {
				
				dto.setImg(img);
			}else {
				dto.setImg("default.jpg");
			}
			
			
		}
		return mapper.profileChange(dto);
	}

	
	
	
	
}
