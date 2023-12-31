package test.spring.mvc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.mvc.bean.EmpDTO;
import test.spring.mvc.service.CrudService;
import test.spring.mvc.service.EmpServiceImpl;

@Controller
@RequestMapping("/emp/*")
public class EmpController {
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	private EmpServiceImpl service;
	
	@Autowired
	private CrudService free;
	
	@RequestMapping("read.do")
	public String read(Model model) {
//		List<EmpDTO> list = service.read();
//		logger.info("mybatis==>"+list);
//		model.addAttribute("list", list);
		return "emp/read";
	}
	
	public String readno(Model model) {
//		List<Integer> list = service.readno();
//		logger.info("mybatis readno==>"+list);
//		model.addAttribute("list", list);
		return "emp/readno";
	}
	@RequestMapping("insert.do")
	public @ResponseBody String insert(Model model,EmpDTO dto) {
//		int r = service.create(dto);
//		logger.info("==emp insert==>"+r);
		return "result";
	}
	@RequestMapping("form.do")
	public String form() {
		return "emp/form";
	}
	
	String s="safs";
	
	
}
