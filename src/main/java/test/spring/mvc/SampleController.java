package test.spring.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.mvc.bean.SampleDTO;

@Controller
@RequestMapping("/1120/*")
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("main.do")
	public String main(SampleDTO dto,HttpServletRequest request,@RequestParam("name") String a , int number, Model model,HttpSession session) {
//		String name= request.getParameter("name");
//		String number= request.getParameter("number");
		model.addAttribute("name", a);
		model.addAttribute("number", number);
		logger.info("parameter->dto.name=="+dto.getName());
		logger.info("parameter->dto.number=="+dto.getNumber());
		logger.info("parameter->name=="+a);
		logger.info("parameter->number=="+number);
		return "1120/main";
		
	}
	
	@RequestMapping("form.do")
	public String test() {
		return "1120/form";
	}
	
	@RequestMapping("test.do")
	public @ResponseBody String test2() {
		return "1120/form";
	}
}
