package test.spring.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@RequestMapping("main2.do")
	public String main2(@RequestParam("name") String [] name) {
		logger.info("parameter list -->"+name);
		logger.info("parameter list -->"+name[0]);
		logger.info("parameter list -->"+name[1]);
		logger.info("parameter list -->"+name[2]);
		return "1120/main";
	}
	
	@RequestMapping("main3.do")
	public String main3(@ModelAttribute("name")String name, @ModelAttribute("number")int number,SampleDTO dto) {
		
		return "1120/main";
	}
	
	@RequestMapping("main4.do")
	public void main4() {
		
	}
	
	@RequestMapping("test2.do")
	public String test2(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg", "helloworld");
		return "redirect:/1120/test3.do?msg2=helloworld2";
	}
	
	@RequestMapping("test3.do")
	public String test3() {
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
	
	@RequestMapping("upload.do")
	public @ResponseBody String upload(MultipartFile save) {
		 String fileName = save.getOriginalFilename();	//파일명
		 long size = save.getSize();	//파일크기
		return fileName+" : "+size;
	}
}
