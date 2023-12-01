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
	private BoardService boardService;

	@RequestMapping("list")
	public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		boardService.list(pageNum, model);
		return "board/list";
	}

	@RequestMapping("writeForm")
	public String writeForm(Model model, @RequestParam(value = "num", defaultValue = "0") int num,
			@RequestParam(value = "ref", defaultValue = "1") int ref,
			@RequestParam(value = "re_step", defaultValue = "0") int re_step,
			@RequestParam(value = "re_level", defaultValue = "0") int re_level) {
		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("re_step", re_step);
		model.addAttribute("re_level", re_level);
		return "board/writeForm";
	}

	@RequestMapping("writePro")
	public String writePro(ArrayList<MultipartFile> files, BoardDTO dto, HttpServletRequest request) {
		int isfile = 0;
		int result = 0;
		for (MultipartFile f : files) {
			if (!f.getOriginalFilename().equals("")) {
				isfile++;
			}
		}
		dto.setIsFile(isfile);
		dto.setIp(request.getRemoteAddr());
		boardService.create(dto);

		if (isfile > 0) {
			String filePath = request.getServletContext().getRealPath("/resources/file/board/");
			result = boardService.fileUpload(files, filePath);
		}

		return "redirect:/free/list";
	}

	@RequestMapping("content")
	public String content(int num, int pageNum, Model model, BoardDTO article) {
		article = boardService.getArticle(num);
		List<String> filenames=boardService.getFileName(num);
		if(filenames.size()>0) {
		filenames=boardService.getFileName(num);
		model.addAttribute("filenames", filenames);
		}
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("article", article);
		
		return "board/content";
	}

	@RequestMapping("updateForm")
	public String updateForm(int num, int pageNum, Model model, BoardDTO article) {
		article = boardService.getArticle(num);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("article", article);
		return "board/updateForm";
	}

    @RequestMapping("deleteForm")
    public String deleteForm(Model model ,int num , int pageNum) {
       model.addAttribute("num",num);
       model.addAttribute("pageNum",pageNum);
       return "board/deleteForm";
       
    }
    @RequestMapping("deletePro")
    public String deletePro(HttpServletRequest request, Model model ,int num ,String passwd, int pageNum , BoardDTO dto) {
       int check =boardService.deleteNum(request,num, passwd);
       model.addAttribute("check",check);
       model.addAttribute("pageNum",pageNum);
       return "board/deletePro";
    }
    @RequestMapping("download")
    public ModelAndView download(String filename, HttpServletRequest request) {
    	String filePath = request.getServletContext().getRealPath("/resources/file/board/");
    	File file = new File(filePath+filename);
    	ModelAndView mv = new ModelAndView("downView","downFile",file);
    	return mv;
    }

}
