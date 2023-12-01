package test.spring.mvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import test.spring.mvc.bean.BoardDTO;
import test.spring.mvc.bean.FreeboardFileDTO;
import test.spring.mvc.bean.MemberDTO;

public interface BoardService {
	public int count();
	public void list(int pageNum,Model model);
	public void create(BoardDTO dto); 
	public BoardDTO getArticle(int num);
	public int updateArticle(BoardDTO dto);
	public int fileUpload(ArrayList<MultipartFile> files,String filePath);
	public List<String> getFileName(int freeboardnum);
	 public int deleteNum(HttpServletRequest request,int num , String passwd);
	 public String readPasswd(int num);
	 public void fileDelete(int freeboardnum);
	
}
