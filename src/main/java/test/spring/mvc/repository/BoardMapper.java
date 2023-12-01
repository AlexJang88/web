package test.spring.mvc.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.mvc.bean.BoardDTO;
import test.spring.mvc.bean.FreeboardFileDTO;

public interface BoardMapper {
	public int count();
	public List<BoardDTO> list(HashMap map);
	public int maxNum();
	public void writeUpdate(BoardDTO dto);
	public void writeInsert(BoardDTO dto);
	public BoardDTO getArticle(int num);
	public int updateArticle(BoardDTO dto);
	public int fileInsert(@Param("freeboardnum")int freeboardnum,@Param("fileName") String fileName);
	public List<String> getFileName(int freeboardnum);
	public int deleteNum(int num);
	public String readPasswd(int num);
	public BoardDTO readNum(int num);
	public void fileDelete(int freeboardnum);
}
