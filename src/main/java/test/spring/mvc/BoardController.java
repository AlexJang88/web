package test.spring.mvc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import test.spring.mvc.bean.BoardDTO;
import test.spring.mvc.bean.FreeboardFileDTO;
import test.spring.mvc.service.BoardService;

@Controller
@RequestMapping("/free/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService service;

	@RequestMapping("list")
	   public String list(Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	      service.list(pageNum, model);
	      
	      
	      return "board/list";
	   }
	   
	   @RequestMapping("writeForm")
	   public String writeForm(Model model,
	                     @RequestParam(value = "num",defaultValue="0")int num,
	                     @RequestParam(value = "ref",defaultValue="1")int ref,
	                     @RequestParam(value = "re_step",defaultValue="0")int re_step,
	                     @RequestParam(value = "re_level",defaultValue="0")int re_level) {
	      model.addAttribute("num",num);
	      model.addAttribute("ref",ref);
	      model.addAttribute("re_step",re_step);
	      model.addAttribute("re_level",re_level);
	      
	      return "board/writeForm";
	   }
	   
	   @RequestMapping("writePro")
	   public String writePro(ArrayList<MultipartFile> files, BoardDTO dto, HttpServletRequest request) {
	      int isfile = 0, result = 0;         
	      for( MultipartFile f : files) {
	         if(!f.getOriginalFilename().equals("")) {
	            isfile++;
	         }
	      }      
	      dto.setIsFile(isfile);
	      dto.setIp(request.getRemoteAddr());      
	      service.create(dto);      
	      
	      if(isfile > 0) {
	         String filePath = request.getServletContext().getRealPath("/resources/file/board/");
	         result = service.fileUpload(files, filePath);
	      }      
	      return "redirect:/free/list";
	   }
	   
	   @RequestMapping("content")
	   public String content(Model model, int num, int pageNum) {
	      BoardDTO board = service.readContent(num);
	      List<String> fileName = service.fileget(num);
	      model.addAttribute("pageNum",pageNum);
	      model.addAttribute("board",board);
	      model.addAttribute("fileName",fileName);
	      return "board/content";
	   }
	   
	   @RequestMapping("updateForm")
	   public String updateForm(Model model, int num, int pageNum) {
	      BoardDTO board = service.update(num);
	      
	      model.addAttribute("pageNum",pageNum);
	      model.addAttribute("board",board);
	      
	      return "board/updateForm";
	   }
	   
	   @RequestMapping("updatePro")
	   public String updatePro(Model model, int pageNum, BoardDTO dto) {
	      int check = service.updateNum(dto);
	      model.addAttribute("check",check);
	      model.addAttribute("pageNum",pageNum);
	      
	      return "board/updatePro";
	   }
	   
	   @RequestMapping("deleteForm")
	   public String deleteForm(Model model, int num, int pageNum) {
	      model.addAttribute("num",num);
	      model.addAttribute("pageNum",pageNum);
	      return "board/deleteForm";
	   }
	   
	   @RequestMapping("deletePro")
	   public String deletePro( Model model, int num, String passwd, int pageNum,HttpServletRequest request) {
	      //HttpServletRequest request 추가
	      //첨부파일삭제, freeboard_file 삭제, 글삭제
	      //String filePath = request.getServelt나중에 추가해야됨
		  String path=request.getServletContext().getRealPath("/resources/file/board/");
	      int check = service.deleteNum(num, passwd,path);
	      model.addAttribute("check",check);
	      model.addAttribute("pageNum",pageNum);
	      
	      return "board/deletePro";
	   }
	   
	   
	  
	   
	   @RequestMapping("download")
	   public ModelAndView download(HttpServletRequest request ,String filename) {
	      //FileDownloadView 클래스를 여기다 사용한다
	      String filePath = request.getServletContext().getRealPath("/resources/file/board/");
	      File file = new File(filePath+filename);
	      ModelAndView mv = new ModelAndView("downView","downFile",file); //뒤에 있는게 보내는값 
	      //"downView"여기로 보낸다  ,"downFile" 키값 , file 밸류 값  //FileDownloadview여기 클레스에
	      return mv;      
	   }

}
