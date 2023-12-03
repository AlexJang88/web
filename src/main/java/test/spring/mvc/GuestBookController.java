package test.spring.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.mvc.bean.GuestBookDTO;
import test.spring.mvc.service.GuestBookService;

@Controller
@RequestMapping("/guest/*")
public class GuestBookController {

	@Autowired
	private GuestBookService service;
	
	@RequestMapping("main")
	public String main(Model model) {
		
		return "/guestbook/main";
	}
	
	@RequestMapping("writeForm")
	public String writeForm() {
		
		return "/guestbook/writeForm";
	}
	public String writePro(HttpSession session,GuestBookDTO dto) {
		String id = (String)session.getAttribute("memId");
		dto.setId(id);
		service.write(dto);
		return "/guestbook/main";
	}
	public String delete(int num) {
		service.delete(num);
		return "/guestbook/main";
	}
	
	
}
