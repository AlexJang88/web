package test.spring.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import test.spring.mvc.bean.MemberDTO;
import test.spring.mvc.service.MemberService;
import test.spring.mvc.service.MemberServiceImpl;
@Controller
@RequestMapping("/user/*")
public class MemberController { 
	
	@Autowired
	private MemberService memberServiceImpl;
	
	@RequestMapping("main.me")
	public String main(HttpSession session,Model model) {
		if(session.getAttribute("memId")!=null) {
			int status = memberServiceImpl.getStatus((String)session.getAttribute("memId"));
			model.addAttribute("status",status);
		}
		return "member/main";
	}
	@RequestMapping("loginPro.me")
	public String loginPro(MemberDTO dto,Model model) {
		int check= memberServiceImpl.loginCheck(dto);
		model.addAttribute("check", check);
		return "member/loginPro";
				
	}
	@RequestMapping("logout.me")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/main.me";
	}
	@RequestMapping("modify.me")
	public String modify(HttpSession session,Model model) {
		String id = (String)session.getAttribute("memId");
		MemberDTO dto = memberServiceImpl.getUser(id);
		model.addAttribute("dto",dto);
		return "member/modify";
		}
	@RequestMapping("modifyForm.me")
	public String modifyForm(HttpSession session,Model model) {
			String id = (String)session.getAttribute("memId");
			MemberDTO dto = memberServiceImpl.getUser(id);
			model.addAttribute("dto",dto);
		return "member/modifyForm";
	}
	@RequestMapping("modifyPro.me")
	public String modifyPro(HttpSession session,MemberDTO dto,Model model) {
		String id = (String)session.getAttribute("memId");
		dto.setId(id);
		memberServiceImpl.userUpdate(dto);
		return "member/modifyPro";
	}
	@RequestMapping("deleteForm.me")
	public String deleteForm() {
		return "member/deleteForm";
	}
	
	@RequestMapping("deletePro.me")
	public String modifyPro(Model model,String passwd,HttpSession session) {
		String id =(String)session.getAttribute("memId");
		int check = memberServiceImpl.userDelete(id, passwd);
		if(check==1) {
			session.invalidate();
		}
		model.addAttribute("check", check);
		return "member/deletePro";
	}
	@RequestMapping("admin/memberlist.ad")
	public String memberlist(Model model) {
		List<MemberDTO> list = memberServiceImpl.memberList();
		model.addAttribute("list", list);
		return "admin/memberList";
	}
	@RequestMapping("admin/statusChange.ad")
	public String changeStatus(String id,int cstatus,int nowStatus,MemberDTO dto) {
		dto.setId(id);
		dto.setStatus(cstatus);
		String result="redirect:/user/admin/memberlist.ad";
		if(nowStatus==cstatus) {
			result="admin/statusCheck";
		}
		memberServiceImpl.adminStatsChange(dto);
		return result;
	}
	@RequestMapping("imgForm.me")
	public String imgForm(HttpSession session,Model model) {
		String id = (String)session.getAttribute("memId");
		MemberDTO dto = memberServiceImpl.getUser(id);
		model.addAttribute("dto",dto);
		return "member/imgForm";
	}
	
	@RequestMapping("imgChange.me")
	public String imgChange(HttpSession session,HttpServletRequest request,MultipartFile profile,MemberDTO dto) {
		dto.setId((String)session.getAttribute("memId"));
		memberServiceImpl.profileChange(request, profile, dto);
		return "redirect:/user/modify.me";
	}
}
