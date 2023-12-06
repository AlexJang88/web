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

	   public int fileUpload(ArrayList<MultipartFile> files, String path);
	   public void create(BoardDTO dto);
	   
	   
	   public void list(int pageNum, Model model);
	   public int count();
	   public BoardDTO readContent(int num);
	   
	   
	   //String path 추가
	   public List<String> fileget(int num); // 사진 찾는곳
	   
	   public int deleteNum(int num, String passwd,String path); //글삭제하기   
	   public BoardDTO update(int num);  // 글수정하기
	   
	   
	   public String readPasswd(int num);
	   public int updateNum(BoardDTO dto);
	   public List<FreeboardFileDTO> fileList(int freeboardnum);

	
}
